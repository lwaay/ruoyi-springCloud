function getUrl(url){
    return `${baseFull}/jimu` + url;
}
//拼接待tokenurl
function getHasTokenUrl(url, id){
    if (id){
        return `${baseFull}/jimu${url}/${id}?token=${token}`;
    }else{
        return `${baseFull}/jimu${url}?token=${token}`;
    }
}
//拼接待域名url
function getOrigin(url){
    return window.location.origin + `${baseFull}/jimu` + url;
}

const apis = {
    //首页
    index: getUrl('/index/'),
    //预览界面
    view: getUrl('/view/'),
    //查询用户名
    userInfo: getUrl('/userinfo'),

    /********************************************报表接口********************************************/
    //查询报表
    excelQuery: getUrl('/excelQuery'),
    //查询报表
    excelQueryByTemplate: getUrl('/excelQueryByTemplate'),
    //保存报表
    saveReport: getUrl('/save'),
    //报表预览
    show: getOrigin('/show'),
    //删除报表
    deleteReport: getUrl('/delete'),
    //图表复制
    reportCopy: getUrl('/reportCopy'),
    //返回图表json配置
    addChart: getUrl('/addChart'),
    //设置模版
    setTemplate: getUrl('/setTemplate'),
    //检测报名名字是否存在
    excelQueryName: getUrl('/excelQueryName'),
    //加载数据源表信息
    loadTable: getUrl('/loadTable'),
    //解析sql
    executeSelectSql: getUrl('/executeSelectSql'),
    // 解析api
    executeSelectApi: getUrl('/executeSelectApi'),

    //校验数据集编码唯一性
    dataCodeExist: getUrl('/dataCodeExist'),
    //获取查询条件信息
    getQueryInfo: getUrl('/getQueryInfo'),

    //导出全部excel
    exportAllExcel: getOrigin('/exportAllExcel'),
    //预览次数
    addViewCount(id){
        return getOrigin('/addViewCount') + '/' + id;
    },
    //确定是否有参数
    checkParam(id){
        return getOrigin('/checkParam') + '/' + id;
    },
    //查询报表
    getReport(id){
        return getHasTokenUrl('/get', id);
    },
    queryIsPage(id){
        return getHasTokenUrl('/queryIsPage', id);
    },
    /********************************************数据源数据集接口********************************************/
    //初始化数据源
    initDataSource:getHasTokenUrl('/initDataSource'),
    //添加数据源
    addDataSource: getUrl('/addDataSource'),
    //删除数据源
    delDataSource: getUrl('/delDataSource'),
    //测试数据源连接
    testConnection: getUrl('/testConnection'),
    //批量删除报表参数
    deleteParamByIds: getUrl('/deleteParamByIds'),
    //批量删除报表参数
    deleteFieldByIds: getUrl('/deleteFieldByIds'),
    //根据sql查询数据集
    qurestSql: getUrl('/qurestSql'),
    //根据接口查询数据集
    qurestApi: getUrl('/qurestApi'),
    //查询数据集
    loadDbData: getUrl('/loadDbData'),
    //报错数据集
    saveDb: getUrl('/saveDb'),
    //删除数据集
    delDbData(dbId){
        return getHasTokenUrl('/delDbData', dbId);
    },

    //查询数据集
    loadDbData(dbId){
        return getHasTokenUrl('/loadDbData', dbId);
    },
    //渲染报表树
    fieldTreeUrl(id){
        return getHasTokenUrl('/field/tree', id);
    },
    /********************************************地图接口********************************************/
    //地图列表
    mapList:getUrl('/map/mapList'),
    //添加地图
    addMapData:getUrl('/map/addMapData'),
    //删除地图
    delMapSource:getUrl('/map/delMapSource'),
    //根据code查询地图
    queryMapByCode:getUrl('/map/queryMapByCode'),
    //根据code查询地图
    queryMapByCodeUseOrigin: getOrigin('/map/queryMapByCode'),

}
