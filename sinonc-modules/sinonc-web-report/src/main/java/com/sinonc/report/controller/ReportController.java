package com.sinonc.report.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecg.modules.jmreport.common.annotation.JimuLoginRequired;
import org.jeecg.modules.jmreport.common.util.CommonUtils;
import org.jeecg.modules.jmreport.common.util.MinioUtil;
import org.jeecg.modules.jmreport.common.util.OssBootUtil;
import org.jeecg.modules.jmreport.common.util.oConvertUtils;
import org.jeecg.modules.jmreport.common.vo.Result;
import org.jeecg.modules.jmreport.config.client.JmReportTokenClient;
import org.jeecg.modules.jmreport.desreport.b.b;
import org.jeecg.modules.jmreport.desreport.entity.JimuReport;
import org.jeecg.modules.jmreport.desreport.entity.JmReportDataSource;
import org.jeecg.modules.jmreport.desreport.entity.JmReportDb;
import org.jeecg.modules.jmreport.desreport.entity.JmReportDbParam;
import org.jeecg.modules.jmreport.desreport.model.QueryVO;
import org.jeecg.modules.jmreport.desreport.service.IJimuDictService;
import org.jeecg.modules.jmreport.desreport.service.IJimuReportService;
import org.jeecg.modules.jmreport.desreport.service.IJmReportDbFieldService;
import org.jeecg.modules.jmreport.desreport.service.IJmReportDbParamService;
import org.jeecg.modules.jmreport.desreport.service.IJmReportDbService;
import org.jeecg.modules.jmreport.desreport.service.IJmReportDbSourceService;
import org.jeecg.modules.jmreport.desreport.service.IJmReportMapService;
import org.jeecg.modules.jmreport.dyndb.vo.JmreportDynamicDataSourceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

@RestController()
@RequestMapping({"/jimu"})
public class ReportController {
    private static final Logger a = LoggerFactory.getLogger(ReportController.class);

    @Value("${jeecg.path.upload:}")
    private String uploadpath;

    @Value("classpath:static/jmreport/json/excel.virtual.json")
    private Resource virtualJson;

    @Value("${jeecg.uploadType:}")
    private String uploadType;

    @Value("${jeecg.jmreport.mode:dev}")
    private String reportMode;

    @Autowired
    private IJimuReportService jmReportDesignService;

    @Autowired
    private IJmReportDbService reportDbService;

    @Autowired
    private IJmReportDbParamService jmReportDbParamService;

    @Autowired
    private IJmReportDbFieldService jmReportDbFieldService;

    @Autowired
    private IJmReportDbSourceService jmReportDbSourceService;

    @Autowired
    private IJmReportMapService jmReportMapService;

    @Autowired
    private JmReportTokenClient jimuTokenClient;

    @Autowired
    private IJimuDictService dictService;

    public ModelAndView a(ModelAndView paramModelAndView, HttpServletRequest paramHttpServletRequest) {
        a.info(" --- 进入报表设计器--- ");
        return e(paramModelAndView, paramHttpServletRequest);
    }

    @RequestMapping({"/demo"})
    public ModelAndView b(ModelAndView paramModelAndView, HttpServletRequest paramHttpServletRequest) {
        a.info(" --- 进入报表设计器demo--- ");
        paramModelAndView.addObject("baseURL", c(paramHttpServletRequest));
        paramModelAndView.setViewName("report/desreport/demo");
        return paramModelAndView;
    }

    @RequestMapping({"/list"})
    public ModelAndView c(ModelAndView paramModelAndView, HttpServletRequest paramHttpServletRequest) {
        a.info(" ---进入报表设计器列表展示 --- ");
        paramModelAndView.addObject("baseURL", c(paramHttpServletRequest));
        paramModelAndView.setViewName("report/desreport/reportlist");
        return paramModelAndView;
    }

    @PostMapping({"/excelCreate"})
    @JimuLoginRequired
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @RequestBody JimuReport paramJimuReport) {
        a.info("============EXCEL模板创建/编辑");
        try {
            String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
            paramJimuReport.setCreateBy(str);
            paramJimuReport = this.jmReportDesignService.excelCreate(paramJimuReport);
        } catch (Exception exception) {
            return Result.error(403, "您没有权限");
        }
        return Result.OK(paramJimuReport);
    }

    @PostMapping({"/excelQueryName"})
    @JimuLoginRequired
    public Result<?> b(HttpServletRequest paramHttpServletRequest, @RequestBody JimuReport paramJimuReport) {
        boolean bool = this.jmReportDesignService.excelQueryName(paramJimuReport);
        if (bool)
            return Result.OK();
        return Result.FAIL("", Boolean.valueOf(bool));
    }

    @GetMapping({"/excelQuery"})
    @JimuLoginRequired
    public Result<?> a(@RequestParam(name = "reportType", required = false) String paramString1, @RequestParam(name = "name") String paramString2) {
        a.info("============EXCEL");
        return this.jmReportDesignService.excelQuery(paramString1, paramString2);
    }

    @GetMapping({"/excelQueryByTemplate"})
    public Result<?> b(@RequestParam(name = "reportType", required = false) String paramString1, @RequestParam(name = "name") String paramString2) {
        return this.jmReportDesignService.excelQueryByTemplate(paramString1, paramString2);
    }

    @GetMapping({"/userinfo"})
    @JimuLoginRequired
    public Result<?> a(HttpServletRequest paramHttpServletRequest) {
        Result result = Result.OK();
        String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
        result.setMessage(str);
        return Result.OK(result);
    }

    @RequestMapping({"/index/{id}"})
    public ModelAndView a(@PathVariable("id") String paramString, ModelAndView paramModelAndView, HttpServletRequest paramHttpServletRequest) {
        if (oConvertUtils.isNotEmpty(paramString))
            paramModelAndView.addObject("id", paramString);
        paramModelAndView.addObject("mode", this.reportMode);
        return e(paramModelAndView, paramHttpServletRequest);
    }

    @RequestMapping({"/view/{id}"})
    public ModelAndView b(@PathVariable("id") String paramString, ModelAndView paramModelAndView, HttpServletRequest paramHttpServletRequest) {
        if (oConvertUtils.isNotEmpty(paramString))
            paramModelAndView.addObject("id", paramString);
        paramModelAndView.addObject("baseURL", c(paramHttpServletRequest));
        paramModelAndView.setViewName("report/desreport/view");
        return paramModelAndView;
    }

    @PostMapping({"/addViewCount/{id}"})
    public Result<?> a(@PathVariable("id") String paramString) {
        JimuReport jimuReport = (JimuReport) this.jmReportDesignService.getById(paramString);
        if (jimuReport == null)
            return Result.error("");
        Long long_ = jimuReport.getViewCount();
        if (long_ == null)
            long_ = Long.valueOf(0L);
        long_ = Long.valueOf(long_.longValue() + 1L);
        jimuReport.setViewCount(long_);
        boolean bool = this.jmReportDesignService.updateById(jimuReport);
        return Result.OK("", Boolean.valueOf(bool));
    }

    @GetMapping({"/checkParam/{id}"})
    public Result<List<JmReportDbParam>> b(@PathVariable(name = "id") String paramString) {
        Result<List<JmReportDbParam>> result = new Result();
        List list = this.jmReportDesignService.queryReportParam(paramString);
        if (list == null || list.size() == 0) {
            result.setSuccess(false);
        } else {
            result.setSuccess(true);
            result.setResult(list);
        }
        return result;
    }

    @RequestMapping({"/show"})
    public Result<?> a(@RequestParam(name = "id") String paramString, HttpServletRequest paramHttpServletRequest) {
        Result<Object> result = Result.OK();
        String str = paramHttpServletRequest.getParameter("params");
        try {
            this.jmReportDesignService.show(paramString, str, result);
            return result;
        } catch (Exception exception) {
            exception.printStackTrace();
            return Result.error(exception.getMessage());
        }
    }

    @PostMapping({"/save"})
    @JimuLoginRequired
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @RequestBody JSONObject paramJSONObject) {
        try {
            JimuReport jimuReport = this.jmReportDesignService.saveReport(paramJSONObject);
            return Result.OK(jimuReport);
        } catch (Exception exception) {
            return Result.error(403, "token");
        }
    }

    @GetMapping({"/get/{id}"})
    @JimuLoginRequired
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @PathVariable("id") String paramString) {
        try {
            QueryWrapper<JimuReport> lambdaQueryWrapper = new QueryWrapper<>();
            lambdaQueryWrapper.eq("id", paramString);
            JimuReport jimuReport =   this.jmReportDesignService.getOne( lambdaQueryWrapper);
            return Result.OK(jimuReport);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Result.error("");
    }

    @GetMapping({"/mockPieData"})
    public Result<?> a() {
        ArrayList<HashMap<Object, Object>> arrayList = new ArrayList();
        String[] arrayOfString = {"IE", "Safari", "Firefox", "Opera", "Chrome"};
        Random random = new Random();
        for (String str : arrayOfString) {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("name", str);
            hashMap.put("value", Integer.valueOf(random.nextInt(1901) + 100));
            arrayList.add(hashMap);
        }
        return Result.OK(arrayList);
    }

    @GetMapping({"/mockChartData"})
    public Result<?> b() {
        ArrayList<HashMap<Object, Object>> arrayList = new ArrayList();
        String[] arrayOfString = {"邮件营销", "联盟广告 ", "视频广告", " 直接访问", "搜索引擎"};
        Random random = new Random();
        for (String str : arrayOfString) {
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("name", str);
            ArrayList<Integer> arrayList1 = new ArrayList();
            for (byte b = 0; b < 7; b++)
                arrayList1.add(Integer.valueOf(random.nextInt(1901) + 100));
            hashMap.put("data", arrayList1);
            arrayList.add(hashMap);
        }
        return Result.OK(arrayList);
    }

    @PostMapping({"/upload"})
    public Result<?> a(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        Result<?> result = new Result();
        try {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) paramHttpServletRequest;
            MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
            String str = "";
            if ("alioss".equals(this.uploadType)) {
                str = OssBootUtil.upload(multipartFile, "designreport/images");
            } else if ("minio".equals(this.uploadType)) {
                str = MinioUtil.upload(multipartFile, "designreport/images");
            } else if ("local".equals(this.uploadType)) {
                str = a(multipartFile, "excel_online");
            }
            result.setMessage(str);
            result.setSuccess(true);
        } catch (Exception exception) {
            result.setSuccess(false);
            a.error(exception.getMessage(), exception);
        }
        return result;
    }

    @CrossOrigin
    @GetMapping({"/img/**"})
    public void b(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) {
        String str = d(paramHttpServletRequest);
        BufferedInputStream bufferedInputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            str = str.replace("..", "");
            if (str.endsWith(","))
                str = str.substring(0, str.length() - 1);
            paramHttpServletResponse.setContentType("image/jpeg;charset=utf-8");
            String str1 = this.uploadpath;
            String str2 = str1 + File.separator + str;
            bufferedInputStream = new BufferedInputStream(new FileInputStream(str2));
            servletOutputStream = paramHttpServletResponse.getOutputStream();
            byte[] arrayOfByte = new byte[1024];
            int i;
            while ((i = bufferedInputStream.read(arrayOfByte)) > 0)
                servletOutputStream.write(arrayOfByte, 0, i);
            paramHttpServletResponse.flushBuffer();
        } catch (IOException iOException) {
            a.error("预览失败" + iOException.getMessage());
        } finally {
            if (bufferedInputStream != null)
                try {
                    bufferedInputStream.close();
                } catch (IOException iOException) {
                    a.error(iOException.getMessage(), iOException);
                }
            if (servletOutputStream != null)
                try {
                    servletOutputStream.close();
                } catch (IOException iOException) {
                    a.error(iOException.getMessage(), iOException);
                }
        }
    }

    private String a(File paramFile) {
        Scanner scanner = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            scanner = new Scanner(paramFile, "utf-8");
            while (scanner.hasNextLine())
                stringBuilder.append(scanner.nextLine());
        } catch (Exception exception) {

        } finally {
            if (scanner != null)
                scanner.close();
        }
        return stringBuilder.toString();
    }

    private ModelAndView e(ModelAndView paramModelAndView, HttpServletRequest paramHttpServletRequest) {
        try {
            paramModelAndView.addObject("baseURL", c(paramHttpServletRequest));
            paramModelAndView.setViewName("report/desreport/index");
        } catch (Exception exception) {
            exception.printStackTrace();
            paramModelAndView.addObject("message", exception.getMessage());
            paramModelAndView.setViewName("report/desreport/error");
        }
        return paramModelAndView;
    }

    private String c(HttpServletRequest paramHttpServletRequest) {
        String str1 = paramHttpServletRequest.getScheme();
        String str2 = paramHttpServletRequest.getServerName();
        int i = paramHttpServletRequest.getServerPort();
        String str3 = paramHttpServletRequest.getContextPath();
        return str1 + "://" + str2 + ":" + i + str3;
    }

    private static String d(HttpServletRequest paramHttpServletRequest) {
        String str1 = (String) paramHttpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String str2 = (String) paramHttpServletRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return (new AntPathMatcher()).extractPathWithinPattern(str2, str1);
    }

    @PostMapping({"/loadTable"})
    public Result<?> b(HttpServletRequest paramHttpServletRequest, @RequestParam("dbSource") String paramString) {
        if (StringUtils.isNotBlank(paramString)) {
            JmreportDynamicDataSourceVo jmreportDynamicDataSourceVo = this.jmReportDbSourceService.getByDbKey(paramString);
            if (jmreportDynamicDataSourceVo == null)
                return Result.error("");
        }
        List list = this.reportDbService.loadDataSourceTable(paramString);
        return Result.OK("", list);
    }

    @PostMapping({"/executeSelectSql"})
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @RequestParam("sql") String
            paramString1, @RequestParam("dbSource") String paramString2) {
        if (StringUtils.isNotBlank(paramString2)) {
            JmreportDynamicDataSourceVo jmreportDynamicDataSourceVo = this.jmReportDbSourceService.getByDbKey(paramString2);
            if (jmreportDynamicDataSourceVo == null)
                return Result.error("");
        }
        ArrayList arrayList = new ArrayList();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("jimuReportHeadId", "");
        Map map = this.reportDbService.executeSelectSql(paramString1, paramString2, hashMap);
        this.reportDbService.parseData(map, arrayList);
        return Result.OK("", arrayList);
    }

    @PostMapping({"/executeSelectApi"})
    @JimuLoginRequired
    public Result<?> b(HttpServletRequest paramHttpServletRequest, @RequestParam("api") String
            paramString1, @RequestParam("method") String paramString2) {
        String str = paramHttpServletRequest.getParameter("token");
        ArrayList arrayList = new ArrayList();
        Map map = this.reportDbService.executeSelectApi(paramString1, paramString2, str);
        this.reportDbService.parseData(map, arrayList);
        return Result.OK(arrayList);
    }

    @PostMapping({"/saveDb"})
    @JimuLoginRequired
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @RequestBody JmReportDb paramJmReportDb) {
        try {
            String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
            paramJmReportDb.setCreateBy(str);
            Date date = new Date();
            paramJmReportDb.setCreateTime(date);
            paramJmReportDb.setUpdateTime(date);
            paramJmReportDb = this.reportDbService.saveDb(paramJmReportDb);
        } catch (Exception exception) {
            return Result.error(403, "");
        }
        return Result.OK(paramJmReportDb);
    }

    @GetMapping({"/field/tree/{reportId}"})
    @JimuLoginRequired
    public Result<?> c(HttpServletRequest paramHttpServletRequest, @PathVariable("reportId") String paramString) {
        List list = new ArrayList();
        try {
            list = this.reportDbService.fieldTree(paramString);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Result.OK(list);
    }

    @GetMapping({"/queryIsPage/{reportId}"})
    @JimuLoginRequired
    public Result<?> d(HttpServletRequest paramHttpServletRequest, @PathVariable("reportId") String paramString) {
        boolean bool = this.reportDbService.queryIsPage(paramString);
        return Result.OK(Boolean.valueOf(bool));
    }

    @GetMapping({"loadDbData/{dbId}"})
    @JimuLoginRequired
    public Result<?> e(HttpServletRequest paramHttpServletRequest, @PathVariable("dbId") String paramString) {
        Map map = new HashMap<>();
        try {
            map = this.reportDbService.loadDbData(paramString);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Result.OK(map);
    }

    @GetMapping({"initDataSource"})
    @JimuLoginRequired
    public Result<?> b(HttpServletRequest paramHttpServletRequest) {
        String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
        List list = this.reportDbService.initDataSource(str);
        return Result.OK(list);
    }

    @GetMapping({"/delDbData/{dbId}"})
    @JimuLoginRequired
    public Result<?> f(HttpServletRequest paramHttpServletRequest, @PathVariable("dbId") String paramString) {
        this.reportDbService.delDbData(paramString);
        return Result.OK();
    }

    @PostMapping({"/deleteParamByIds"})
    @JimuLoginRequired
    public Result<?> b(HttpServletRequest paramHttpServletRequest, @RequestBody JSONObject paramJSONObject) {
        JSONArray jSONArray = paramJSONObject.getJSONArray("selectIds");
        String str1 = paramJSONObject.getString("id");
        String str2 = paramJSONObject.getString("dbDynSql");
        this.jmReportDbParamService.deleteParamByIdsAndUpdateDbSql(jSONArray, str1, str2);
        return Result.OK();
    }

    @DeleteMapping({"/deleteFieldByIds"})
    @JimuLoginRequired
    public Result<?> c(HttpServletRequest paramHttpServletRequest, @RequestBody JSONObject paramJSONObject) {
        JSONArray jSONArray = paramJSONObject.getJSONArray("selectIds");
        this.jmReportDbFieldService.deleteFieldByIds(jSONArray);
        return Result.OK();
    }

    @DeleteMapping({"/delete"})
    @ResponseBody
    @JimuLoginRequired
    public Result<?> g(HttpServletRequest paramHttpServletRequest, @RequestParam(name = "id", required = true) String paramString) {
        JimuReport jimuReport = new JimuReport();
        jimuReport.setId(paramString);
        jimuReport.setDelFlag(Integer.valueOf(1));
        this.jmReportDesignService.updateById(jimuReport);
        return Result.OK("", Boolean.valueOf(true));
    }

    @GetMapping({"/reportCopy"})
    @ResponseBody
    @JimuLoginRequired
    public Result<?> h(HttpServletRequest paramHttpServletRequest, @RequestParam(name = "id", required = true) String paramString) {
        try {
            String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
            JimuReport jimuReport = new JimuReport();
            jimuReport.setCreateBy(str);
            Date date = new Date();
            jimuReport.setCreateTime(date);
            jimuReport.setUpdateTime(date);
            jimuReport.setId(paramString);
            this.jmReportDesignService.reportCopy(jimuReport);
        } catch (Exception exception) {
            return Result.error(403, "");
        }
        return Result.OK("", Boolean.valueOf(true));
    }

    @GetMapping({"/setTemplate"})
    @ResponseBody
    @JimuLoginRequired
    public Result<?> a(HttpServletRequest
                               paramHttpServletRequest, @RequestParam(name = "id", required = true) String
                               paramString, @RequestParam(name = "template", required = true) Integer paramInteger) {
        JimuReport jimuReport = new JimuReport();
        jimuReport.setId(paramString);
        jimuReport.setTemplate(paramInteger);
        boolean bool = this.jmReportDesignService.updateById(jimuReport);
        return Result.OK("", Boolean.valueOf(bool));
    }

    @PostMapping({"/importExcel"})
    @ResponseBody
    @JimuLoginRequired
    public Result<Object> a(HttpServletRequest paramHttpServletRequest, @RequestParam("file") MultipartFile
            paramMultipartFile) throws IOException {
        Result<Object> result = new Result();
        Map map = this.jmReportDesignService.importExcel(paramMultipartFile);
        if (map.size() > 0 && !map.isEmpty()) {
            result.setResult(map);
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }

    @GetMapping({"/exportExcel/{excelConfigId}/{page}/{pageSize}"})
    @ResponseBody
    public Result<Object> a(@PathVariable(name = "excelConfigId") String
                                    paramString1, @PathVariable(name = "page") String paramString2, @PathVariable(name = "pageSize") String
                                    paramString3, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws
            IOException {
        HashMap<String, String[]> hashMap = new HashMap<>(paramHttpServletRequest.getParameterMap());
        boolean bool = hashMap.containsKey("token");
        if (bool)
            hashMap.remove("token");
        Map map = this.jmReportDesignService.exportExcel(paramString1, paramString2, paramString3, hashMap);
        File file = new File(map.get("path").toString());
        paramHttpServletResponse.setHeader("Content-Disposition", "attachment; fileName=" + file.getName() + ";filename*=utf-8''" + URLEncoder.encode(file.getName(), "UTF-8"));
        ServletOutputStream servletOutputStream = paramHttpServletResponse.getOutputStream();
        XSSFWorkbook xSSFWorkbook = (XSSFWorkbook) map.get("workbook");
        xSSFWorkbook.write((OutputStream) servletOutputStream);
        return Result.OK("", Boolean.valueOf(true));
    }

    @PostMapping({"/exportAllExcel"})
    @ResponseBody
    public Result<Object> a(HttpServletRequest paramHttpServletRequest, HttpServletResponse
            paramHttpServletResponse, @RequestBody JSONObject paramJSONObject) throws IOException {
        HashMap<String, String[]> hashMap1 = new HashMap<>(paramHttpServletRequest.getParameterMap());
        boolean bool = hashMap1.containsKey("token");
        if (bool)
            hashMap1.remove("token");
        String str = paramJSONObject.getString("excelConfigId");
        JSONObject jSONObject = paramJSONObject.getJSONObject("queryParam");
        JSONArray jSONArray = paramJSONObject.getJSONArray("base64Arry");
        Map map = this.jmReportDesignService.exportAllExcel(str, hashMap1, (List) jSONArray, jSONObject);
        File file = new File(map.get("name").toString());
        paramHttpServletResponse.setContentType("application/force-download");
        paramHttpServletResponse.setHeader("Content-Disposition", "attachment; fileName=" + file.getName() + ";filename*=utf-8''" + URLEncoder.encode(file.getName(), "UTF-8"));
        ServletOutputStream servletOutputStream = paramHttpServletResponse.getOutputStream();
        XSSFWorkbook xSSFWorkbook = (XSSFWorkbook) map.get("workbook");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HashMap<Object, Object> hashMap2 = new HashMap<>();
        try {
            xSSFWorkbook.write(byteArrayOutputStream);
            String str1 = Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
            hashMap2.put("file", str1);
            hashMap2.put("success", Boolean.valueOf(true));
            hashMap2.put("name", file.getName());
        } catch (IOException iOException) {
            iOException.printStackTrace();
            hashMap2.put("error", iOException.getMessage());
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        return Result.OK(hashMap2);
    }

    @RequestMapping({"/print"})
    public ModelAndView d(ModelAndView paramModelAndView, HttpServletRequest paramHttpServletRequest) {
        paramModelAndView.setViewName("report/desreport/print");
        return paramModelAndView;
    }

    @PostMapping({"/putFile"})
    @ResponseBody
    @JimuLoginRequired
    public Result<?> i(HttpServletRequest paramHttpServletRequest, @RequestParam String paramString) {
        Result<?> result = new Result();
        try {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) paramHttpServletRequest;
            MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
            String str = "";
            if ("alioss".equals(this.uploadType)) {
                str = OssBootUtil.upload(multipartFile, "designreport/images");
            } else if ("minio".equals(this.uploadType)) {
                str = MinioUtil.upload(multipartFile, "designreport/images");
            }
            JimuReport jimuReport = new JimuReport();
            if (StringUtils.isNotEmpty(str) && StringUtils.isNotEmpty(paramString)) {
                jimuReport.setId(paramString);
                jimuReport.setThumb(str);
                this.jmReportDesignService.updateById(jimuReport);
                result.setMessage("");
            }
        } catch (Exception exception) {
            result.setSuccess(false);
            result.setMessage("");
            a.error(exception.getMessage(), exception);
        }
        return result;
    }

    @GetMapping({"/download/image"})
    @ResponseBody
    public Result<Object> c(HttpServletRequest paramHttpServletRequest, HttpServletResponse
            paramHttpServletResponse) throws IOException {
        String str1 = paramHttpServletRequest.getParameter("imageUrl");
        Result<Object> result = new Result();
        if (StringUtils.isEmpty(str1)) {
            result.setSuccess(false);
            return result;
        }
        if (!str1.contains("http")) {
            result.setSuccess(true);
            result.setResult(str1);
        }
        String str2 = File.separator + "designreport" + File.separator + "images" + File.separator;
        String str3 = str1.substring(str1.lastIndexOf("/") + 1);
        File file1 = new File(this.uploadpath + str2);
        if (!file1.exists())
            file1.mkdirs();
        File file2 = new File(this.uploadpath + str2 + str3);
        if (!file2.exists())
            HttpUtil.downloadFile(str1, file2);
        result.setSuccess(true);
        result.setResult(str2 + str3);
        return result;
    }

    private String a(MultipartFile paramMultipartFile, String paramString) {
        try {
            String str1 = this.uploadpath;
            String str2 = null;
            File file1 = new File(str1 + File.separator + paramString + File.separator);
            if (!file1.exists())
                file1.mkdirs();
            String str3 = paramMultipartFile.getOriginalFilename();
            str3 = CommonUtils.getFileName(str3);
            if (str3.indexOf(".") != -1) {
                str2 = str3.substring(0, str3.lastIndexOf(".")) + "_" + System.currentTimeMillis() + str3.substring(str3.indexOf("."));
            } else {
                str2 = str3 + "_" + System.currentTimeMillis();
            }
            String str4 = file1.getPath() + File.separator + str2;
            File file2 = new File(str4);
            FileCopyUtils.copy(paramMultipartFile.getBytes(), file2);
            String str5 = null;
            if (oConvertUtils.isNotEmpty(paramString)) {
                str5 = paramString + File.separator + str2;
            } else {
                str5 = str2;
            }
            if (str5.contains("\\"))
                str5 = str5.replace("\\", "/");
            return str5;
        } catch (IOException iOException) {
            a.error(iOException.getMessage(), iOException);
            return "";
        }
    }

    @PostMapping({"/addDataSource"})
    @JimuLoginRequired
    public Result<?> a(HttpServletRequest paramHttpServletRequest, @RequestBody JmReportDataSource
            paramJmReportDataSource) {
        try {
            String str = this.jimuTokenClient.getUsername(paramHttpServletRequest);
            paramJmReportDataSource.setCreateBy(str);
            Date date = new Date();
            paramJmReportDataSource.setCreateTime(date);
            paramJmReportDataSource.setUpdateTime(date);
            this.reportDbService.saveDbSource(paramJmReportDataSource);
        } catch (Exception exception) {
            return Result.error(403, "");
        }
        if (ObjectUtil.isNotEmpty(paramJmReportDataSource.getId()))
            return Result.OK("", Boolean.valueOf(true));
        return Result.OK("", Boolean.valueOf(true));
    }

    @PostMapping({"/querySourceCode"})
    @JimuLoginRequired
    public Result<?> b(HttpServletRequest paramHttpServletRequest, @RequestBody JmReportDataSource
            paramJmReportDataSource) {
        boolean bool = this.reportDbService.querySourceCode(paramJmReportDataSource);
        return Result.OK(Boolean.valueOf(bool));
    }

    @PostMapping({"/delDataSource"})
    @JimuLoginRequired
    public Result<?> c(HttpServletRequest paramHttpServletRequest, @RequestBody JmReportDataSource
            paramJmReportDataSource) {
        this.reportDbService.delDataSource(paramJmReportDataSource);
        return Result.OK("", Boolean.valueOf(true));
    }

    @PostMapping({"/testConnection"})
    public Result a(@RequestBody JmreportDynamicDataSourceVo paramJmreportDynamicDataSourceVo) {
        Connection connection = null;
        try {
            Class.forName(paramJmreportDynamicDataSourceVo.getDbDriver());
            connection = DriverManager.getConnection(paramJmreportDynamicDataSourceVo.getDbUrl(), paramJmreportDynamicDataSourceVo.getDbUsername(), paramJmreportDynamicDataSourceVo.getDbPassword());
            if (connection != null)
                return Result.OK("", Boolean.valueOf(true));
            return Result.OK("", Boolean.valueOf(true));
        } catch (ClassNotFoundException classNotFoundException) {
            a.error(classNotFoundException.toString());
            return Result.error("");
        } catch (Exception exception) {
            a.error(exception.toString());
            return Result.error("" + exception.getMessage());
        } finally {
            try {
                if (connection != null && !connection.isClosed())
                    connection.close();
            } catch (SQLException sQLException) {
                a.error(sQLException.toString());
            }
        }
    }

    @RequestMapping({"/qurestApi"})
    public Result<?> b(@RequestParam(name = "apiSelectId") String paramString, HttpServletRequest
            paramHttpServletRequest) {
        String str = "";
        JmReportDb jmReportDb = (JmReportDb) this.reportDbService.getById(paramString);
        if ("0".equals(jmReportDb.getApiMethod())) {
            str = HttpUtil.createRequest(Method.GET, jmReportDb.getApiUrl()).execute().body();
        } else {
            str = HttpUtil.createRequest(Method.POST, jmReportDb.getApiUrl()).execute().body();
        }
        JSONObject jSONObject = JSONObject.parseObject(str);
        return Result.OK(jSONObject);
    }

    @RequestMapping({"/qurestSql"})
    public Result<?> c(@RequestParam(name = "apiSelectId") String paramString, HttpServletRequest
            paramHttpServletRequest) {
        JmReportDb jmReportDb = (JmReportDb) this.reportDbService.getById(paramString);
        List list = this.reportDbService.qurestechSql(jmReportDb);
        list = this.jmReportDbFieldService.replaceDbCode(jmReportDb, list);
        return Result.OK(b.b(list));
    }

    @GetMapping({"/dataCodeExist"})
    public Result<Boolean> c(@RequestParam(name = "reportId") String
                                     paramString1, @RequestParam(name = "code") String paramString2) {
        JmReportDb jmReportDb=new JmReportDb();
        LambdaQueryWrapper lambdaQueryWrapper = (LambdaQueryWrapper) ((LambdaQueryWrapper) (new LambdaQueryWrapper()).eq(jmReportDb.getJimuReportId(), paramString1)).eq(jmReportDb.getDbCode(), paramString2);
        int i = this.reportDbService.count((Wrapper) lambdaQueryWrapper);
        return Result.OK(Boolean.valueOf((i > 0)));
    }

    @GetMapping({"/getQueryInfo"})
    public Result<List<QueryVO>> c(@RequestParam(name = "reportId") String paramString) {
        List list = this.jmReportDesignService.getReportQueryInfo(paramString);
        return Result.OK(list);
    }

    @RequestMapping({"/addChart"})
    @JimuLoginRequired
    public Result<?> d(@RequestParam(name = "chartType") String paramString, HttpServletRequest
            paramHttpServletRequest) {
        JSONObject jSONObject = this.jmReportDesignService.addChart(paramString);
        return Result.OK(jSONObject);
    }
}
