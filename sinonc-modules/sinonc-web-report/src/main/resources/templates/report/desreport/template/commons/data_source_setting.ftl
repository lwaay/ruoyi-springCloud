<style>
    .nameCla{
        color:#333;
    }
    nameCla:hover{
        color:#2d8cf0;
    }
    .on{
        color: #2d8cf0;
    }
</style>
<script type="text/x-template" id="data-source-setting-template">
    <div>
        <!-- 新增数据集 弹框-begin -->
        <Modal
                fullscreen=true
                :loading="loading"
                width="100%"
                v-model="sqlModal"
                :title="moduleTitle"
                @on-cancel="clearDb"
                @on-ok="saveDb">
            <Row>
                <i-col span="4" v-if="sqlForm.dbType == 0">
                    <Card style="height: 800px;">
                        <div>
                            <i-select :model.sync="sqlForm.dbSource" v-model="sqlForm.dbSource" style="width:150px" @on-change="selectdbSource" clearable >
                                <i-option v-for="item in sourceTab.data" :value="item.id">{{ item.name }}</i-option>
                            </i-select>
                            <i-button @click="sourceManage" v-if="sqlForm.dbType == 0" type="primary">数据源维护</i-button>
                        </div>
                        <div style="margin-top: 10px" v-if="tableList&&tableList.length>0">
                           <#--表名列表-->
                               <List size="small" border  style="overflow-y: auto;height: 720px;" ref="taList">
                                   <ListItem v-for="(item,index) in tableList" :id="index">
                                       <a href="Javascript:void(0)" @click="tableNameClick(item,index)" :class="{on:currentIndex === index,'nameCla':index===index}">{{item.Comment?item.Comment:item.Name}}</a>
                                   </ListItem>
                               </List>
                        </div>
                    </Card>
                </i-col>
                <i-col :span="secondSpan">
                    <div style="margin-left: 20px">
                        <i-form ref="sqlForm"
                                :model="sqlForm"
                                :rules="sqlFormValidate"
                                inline :label-width="85">
                            <Row>
                                <i-col span="6">
                                    <form-item prop="dbCode" label="编码:">
                                        <i-input :disabled ="sqlForm.id!='' && sqlForm.id!=undefined" style="width: 253px" type="text" v-model="sqlForm.dbCode" placeholder="请输入编码">
                                        </i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="6">
                                    <form-item prop="dbChName" label="名称:">
                                        <i-input type="text" style="width: 300px" v-model="sqlForm.dbChName" placeholder="请输入名称">
                                        </i-input>
                                    </form-item>
                                </i-col>
                                <i-col span="4">
                                    <form-item>
                                    <#--<Checkbox :checked.sync="sqlForm.isPage" v-if="addIsPage == true" disabled v-model="sqlForm.isPage">是否分页</Checkbox>-->
                                        <Checkbox :checked.sync="sqlForm.isPage" v-model="sqlForm.isPage" @on-change="checkChange">是否分页</Checkbox>
                                    </form-item>
                                </i-col>
                                <i-col span="6" v-if="sqlForm.dbType == 1">
                                    <form-item prop="apiMethod" label="请求方式:">
                                        <i-select  style="width: 253px" v-model="sqlForm.apiMethod" placeholder="请输入请求方式">
                                            <i-option value="0">get</i-option>
                                            <i-option value="1">post</i-option>
                                        </i-select>
                                    </form-item>
                                </i-col>
                            </Row>
                            <Row style="margin-top: 1%;">
                                <i-col span="21">
                                    <form-item prop="dbDynSql" label="报表SQL:" v-if="sqlForm.dbType == 0">
                                        <i-input v-model="sqlForm.dbDynSql"  @on-blur="dbDynSqlBlur"  type="textarea" :rows="4"  placeholder="请输入查询SQL" style="min-height: 120px;max-height: 620px;width:950px">
                                        </i-input>
                                    </form-item>
                                    <form-item prop="apiUrl" label="Api地址:" v-else="sqlForm.dbType == 1">
                                        <i-input v-model="sqlForm.apiUrl" type="textarea" :rows="4"  placeholder="请输入Api地址" style="min-height: 120px;max-height: 620px;width:950px">
                                        </i-input>
                                    </form-item>
                                    <i-button @click="handleSQLAnalyze" v-if="sqlForm.dbType == 0" type="primary">SQL解析</i-button>
                                    <i-button @click="handleApiAnalyze" v-if="sqlForm.dbType == 1" type="primary">Api解析</i-button>
                                </i-col>
                                <i-col span="3">

                                </i-col>
                            </Row>
                        </i-form>
                        <Tabs v-model="tabValue" style="margin-top: 15px">
                            <tab-pane label="动态报表配置明细" name="1">
                                <i-button type="primary" @click="removeFieldTable" v-if="tab1.selectParamTables.length>0">删除</i-button>
                                <i-table style="padding-bottom: 10%;" ref="dynamicTable" @on-select="selectField" @on-select-all="selectFieldAll" @on-select-all-cancel="cancelFieldAll" @on-select-cancel="cancelField" stripe :columns="tab1.columns" :data="tab1.data" :height="tableHeight"></i-table>
                            </tab-pane>
                            <tab-pane label="报表参数" name="2">
                                <i-button type="primary" @click="addParamTable">新增</i-button>
                                <i-button type="primary" @click="removeParamTable" v-if="tab2.selectParamTables.length>0">删除</i-button>
                                <i-table ref="paramTable" @on-select="selectParam" @on-select-all="selectParamAll" @on-select-all-cancel="cancelParamAll" @on-select-cancel="cancelParam" stripe :columns="tab2.columns" :data="tab2.data" :height="paramTableHeight"></i-table>
                            </tab-pane>
                        </Tabs>
                    </div>
                </i-col>
            </Row>
        </Modal>
        <!-- 新增数据集 弹框-end -->

       <#--数据源维护 弹窗-begin-->
        <Modal
                class-name="vertical-center-modal"
                fullscreen=true
                :loading="loading"
                v-model="sourceModal"
                title="数据源维护"
                @on-ok="saveSourceDb">
            <Row>
                <i-col span="3">
                    <i-button @click="addDataSource" type="primary">新增</i-button>
                </i-col>
            </Row>
            <template>
                <i-table border :columns="sourceTab.columns" :data="sourceTab.data"  style="margin-top: 1%;"></i-table>
            </template>
        </Modal>

        <Modal :loading="loading" v-model="visibleData" title="数据源" :width="35" @on-cancel="clearDbSou" @on-ok="saveDataSource">
            <div style="padding-right: 30px">
                <i-form ref="dataSource" :model="dataSource" :rules="dataFormValidate" label-colon :label-width="100" >

                    <form-item prop="name" label="数据源名称" style="height:50px">
                        <i-input v-model="dataSource.name" placeholder="请输入数据源名称"></i-input>
                    </form-item>

                    <form-item prop="dbType" label="数据源类型" style="height:50px">
                        <i-select :model.sync="dataSource.dbType" v-model="dataSource.dbType" @on-change="selectdbType">
                            <i-option v-for="item in dataSourceTypeList" :value="item.value">{{ item.label }}</i-option>
                        </i-select>
                    </form-item>

                    <form-item prop="dbDriver" label="驱动类" style="height:50px">
                        <i-input v-model="dataSource.dbDriver" placeholder="请输入驱动类"></i-input>
                    </form-item>

                    <form-item prop="dbUrl" label="数据源地址" style="height:50px">
                        <i-input v-model="dataSource.dbUrl" placeholder="请输入数据源地址"></i-input>
                    </form-item>

                    <form-item prop="dbUsername" label="用户名" style="height:50px">
                        <i-input v-model="dataSource.dbUsername" placeholder="请输入用户名"></i-input>
                    </form-item>

                    <form-item prop="dbPassword" label="密码" style="height:50px;width: 100%;">
                        <i-input style="width: calc(100% - 60px)" type="password" password v-model="dataSource.dbPassword" placeholder="请输入密码"></i-input>
                        <i-button size="small" style="width: 50px" @click="dataSourceTest" type="primary">测试</i-button>
                    </form-item>
                </i-form>
            </div>
        </Modal>
    <#--数据源维护 弹窗-end-->

    <#--删除确认弹窗-begin-->
        <Modal
                v-model="deleteParamModel"
                @on-ok="deleteParamTable"
                title="确认删除">
            <p><Icon type="ios-alert"  color="#f90" size="20px"></Icon>是否删除选中数据?</p>
        </Modal>
        <Modal
                v-model="deleteFieldModel"
                @on-ok="deleteFieldTable"
                title="确认删除">
            <p><Icon type="ios-alert"  color="#f90" size="16px"></Icon>是否删除选中配置?</p>
        </Modal>
    <#--删除确认弹窗-end-->

    </div>
</script>

<script>
    Vue.component('j-data-source-setting', {
        template: '#data-source-setting-template',
        props: {
            settings: {
                type: Object,
                required: false,
                default: () => {
                }
            }
        },
        data(){
            return {
                loading:true,
                menuitem : "printinfo",
                currentIndex:-1,
                moduleTitle: "",
                tableHeight: 0,
                paramTableHeight: 0,
                tabValue:"1",
                deleteParamModel:false,
                deleteFieldModel:false,
                dataSourceTypeList:[{value: 'MYSQL5.5',label: 'MySQL5.5'},{value: 'MYSQL5.7',label: 'MySQL5.7'},{value: 'ORACLE',label: 'Oracle'},{value: 'SQLSERVER',label: 'SQLServer'}],
                designerObj:{
                    id:"",
                    name:"",
                    type:"printinfo"
                },
                tab1: {
                    selectParamTables:[],
                    data: [],
                    columns: [
                        {
                            type: 'selection',
                            width: 35,
                            align: 'center'
                        },
                        {
                            type: 'index',
                            width: 60,
                            align: 'center'
                        },
                        {
                            title: '字段名',
                            key: 'fieldName',
                            /*width: '220',*/
                            render: (h, params) => {
                                return this.renderInput(h, params, 'fieldName','tab1')
                            }
                        },
                        {
                            title: '排序',
                            key: 'orderNum',
                            /*width: '80',*/
                            render: (h, params) => {
                                return this.renderInput(h, params, 'orderNum','tab1')
                            }
                        },
                        {
                            title: '字段文本',
                            /*width: '220',*/
                            key: 'fieldText',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'fieldText','tab1')
                            }
                        },
                        {
                            title: '类型',
                            /*width: '140',*/
                            key: 'widgetType',
                            render: (h, params) => {
                                let options = [
                                    // 下拉选项
                                    {title: '数值类型', value: 'number'},
                                    {title: '字符类型', value: 'string'},
                                    {title: '日期类型', value: 'date'},
                                    {title: '时间类型', value: 'datetime'}
                                ];

                                return h('i-select', {
                                            props: {
                                                size:'small',
                                                value: this.tab1.data[params.index].widgetType,
                                            },
                                            on: {
                                                'on-change': (value) => {
                                                    _this.tab1.data[params.index].widgetType = value;
                                                }
                                            },
                                        },
                                        options.map(item => {
                                            return h('i-option', {
                                                props: {
                                                    value: item.value
                                                }
                                            }, item.title)
                                        })
                                );
                            }
                        },
                        {
                            title: '字典code',
                            /*width: '220',*/
                            key: 'dictCode',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'dictCode','tab1')
                            }
                        },
                        {
                            title: '查询',
                            width: '80',
                            key: 'searchFlag',
                            render:(h, params)=> {
                                return h('Checkbox',{
                                    props: {
                                        size:'small',
                                        value: this.tab1.data[params.index].searchFlag,
                                        trueValue: 1,
                                        falseValue: 0
                                    },
                                    on: {
                                        'on-change': (value) => {
                                            _this.tab1.data[params.index].searchFlag = value;
                                            if(value==0){
                                                // _this.tab1.data[params.index].searchMode = null;
                                            }
                                        }
                                    }
                                });
                            }
                        },
                        {
                            title: '查询模式',
                            /*width: '140',*/
                            key: 'searchMode',
                            render: (h, params) => {
                                let options = [ // 下拉选项
                                    {title: '单条件查询', value: 1},
                                    {title: '范围查询', value: 2},
                                    {title: '多选查询', value: 3, tip:'须设置字典code'}
                                ];

                                return h('i-select', {
                                            props: {
                                                size:'small',
                                                value: this.tab1.data[params.index].searchMode
                                            },
                                            on: {
                                                'on-change': (value) => {
                                                    console.log(this.tab1.data)
                                                    _this.tab1.data[params.index].searchMode = value;
                                                    // this.tab1.data
                                                }
                                            },
                                        },
                                        options.map(item => {
                                            let optionObject = {
                                                props: {
                                                    value: item.value
                                                }
                                            }
                                            if(item.tip){
                                                optionObject['attrs'] = {title: item.tip}
                                            }
                                            return h('i-option', optionObject, item.title)
                                        })
                                );
                            }
                        }
                    ]
                },
                tab2:{
                    selectParamTables:[],
                    data: [],
                    columns: [
                        {
                            type: 'selection',
                            width: 35,
                            align: 'center'
                        },
                        {
                            type: 'index',
                            width: 60,
                            align: 'center'
                        },
                        {
                            title: '参数',
                            key: 'paramName',
                            width: '300',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'paramName','tab2')
                            }
                        },
                        {
                            title: '参数文本',
                            key: 'paramTxt',
                            width: '300',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'paramTxt','tab2')
                            }
                        },
                        {
                            title: '默认值',
                            key: 'paramValue',
                            width: '300',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'paramValue','tab2')
                            }
                        },
                        {
                            title: '排序',
                            key: 'orderNum',
                            width: '300',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'orderNum','tab2')
                            }
                        },
                    ]
                },
                sqlForm: {
                    dbCode: "",
                    dbChName: "",
                    dbDynSql: "",
                    dbType: "",
                    apiUrl: "",
                    apiMethod: "0",
                    isPage:false,
                    dbSource:""
                },
                sqlModal: false,
                sqlFormValidate:{
                    dbCode:[
                        { required: true, message: '编码不能为空', trigger: 'blur' },
                        { validator: this.validateCodeExist, trigger: 'blur' }
                    ],
                    dbChName:[
                        { required: true, message: '名称不能为空', trigger: 'blur' }
                    ],
                    dbDynSql:[
                        { required: true, message: '报表SQL不能为空', trigger: 'blur' }
                    ],
                    apiUrl:[
                        { required: true, message: '请求地址不能为空', trigger: 'blur' }
                    ]
                },
                sourceModal: false,
                sourceTab:{
                    //selectParamTables:[],
                    data: [],
                    columns: [
                        {
                            type: 'index',
                            width: 60,
                            align: 'center'
                        },
                        {
                            title: '数据源名称',
                            key: 'name'
                        },
                        {
                            title: '数据库类型',
                            key: 'dbType',
                            render: (h, params) => {
                                switch (params.row.dbType) {
                                    case "MYSQL":
                                        return h('span', 'MySQL');
                                        break;
                                    case "ORACLE":
                                        return h('span', 'Oracle');
                                        break;
                                    case "SQLSERVER":
                                        return h('span', 'SQLServer');
                                        break;
                                }
                            }
                        },
                        {
                            title: '用户名',
                            key: 'dbUsername'
                        },
                        {
                            title: '操作',
                            key: 'action',
                            width: 150,
                            align: 'center',
                            render: (h, params) => {
                                return this.renderButton(h, params);
                            }
                        }
                    ]
                },
                visibleData: false,
                dataSource: {
                    id: "",
                    code: "",
                    reportId:"",
                    name: "",
                    dbType: "",
                    dbDriver: "",
                    dbUrl: "",
                    dbUsername:"",
                    dbPassword:""
                },
                dataFormValidate:{
                    name:[
                        { required: true, message: '数据源名称不能为空', trigger: 'blur' }
                    ],
                    dbType:[
                        { required: true, message: '数据源类型不能为空', trigger: 'blur' }
                    ],
                    dbDriver:[
                        { required: true, message: '驱动类不能为空', trigger: 'blur' }
                    ],
                    dbUrl:[
                        { required: true, message: '数据源地址不能为空', trigger: 'blur' }
                    ],
                    dbUsername:[
                        { required: true, message: '用户名不能为空', trigger: 'blur' }
                    ],
                    dbPassword:[
                        { required: true, message: '密码不能为空', trigger: 'blur' }
                    ]
                },
                tableList:[]
            }
        },
        mounted(){
            //多数据源
            this.initDataSource();
        },
        computed: {
            secondSpan: function () {
                return this.sqlForm.dbType == "0" ? 20 : 24;
            }
        },
        methods: {
            onMenuSelect(name){
                this.menuitem = name;
                let modalFlag = false;
                if (name === "sqlInfo") {
                    //sql
                    this.moduleTitle = "SQL数据集";
                    this.sqlForm.dbType = "0";
                    modalFlag = true;
                }else if(name==="apiInfo"){
                    //api
                    this.moduleTitle = "Api数据集";
                    this.sqlForm.dbType = "1";
                    modalFlag = true;
                }
                this.sqlForm.dbCode = "";
                this.sqlForm.dbChName = "";
                this.sqlForm.dbDynSql = "";
                this.sqlForm.apiUrl = "";
                this.sqlForm.dbSource = "";
                this.tab1.data = [];
                this.tab2.data = [];
                this.sqlModal = modalFlag;
            },
            editById(dbId){
                this.tabValue="1";
                $http.get({url:apis.loadDbData(dbId),success:(result)=>{
                        console.log('result=====',result);
                        let reportResult = result;
                        if(!reportResult){
                            return;
                        }
                        //设置数据
                        this.sqlForm = reportResult.reportDb;
                        var bol = reportResult.reportDb.isPage;
                        this.tab1.data=reportResult.fieldList;
                        if(this.tab1.data){
                            this.tab1.data.forEach((item,index)=>{
                                item.tableIndex = index+1;
                            })
                        }
                        this.tab2.data=reportResult.paramList;
                        if(this.tab2.data){
                            this.tab2.data.forEach((item,index)=>{
                                item.tableIndex = index+1;
                            })
                        }
                        if (this.sqlForm.dbType === "0"){
                            this.moduleTitle = "SQL数据集";
                        } else {
                            this.moduleTitle = "Api数据集";
                        }
                        if (bol=='1'){
                            this.sqlForm.isPage = true;
                        }else {
                            this.sqlForm.isPage = false;
                        }
                        this.handleDbSourceTable()
                        this.sqlModal = true;
                    }});
            },
            clearDb(){
                this.getReport();
                for(let key in this.sqlForm){
                    this.sqlForm[key] = "";
                }
                this.sqlForm.isPage=true;
                this.sqlForm.apiMethod="0";
                this.tab1.data = [];
                this.tab2.data = [];
            },
            getReport(){
                $http.get({url:apis.getReport(excel_config_id),success:(result)=>{
                    console.log("result====>",result)
                    if (result){
                        this.$emit('cancelback',result)
                        this.designerObj = result;
                    }
                }});
            },
            saveDb(){
                this.$refs.sqlForm.validate((valid)=>{
                    if(valid){
                        //保存表单
                        let reportDb = {};
                        reportDb.id = this.sqlForm.id;
                        reportDb.jimuReportId = excel_config_id;
                        reportDb.dbCode = this.sqlForm.dbCode;
                        reportDb.dbChName = this.sqlForm.dbChName;
                        reportDb.dbType = this.sqlForm.dbType;
                        reportDb.dbSource = this.sqlForm.dbSource;
                        if (this.sqlForm.isPage){
                            /*if (this.addIsPage){
                                reportDb.isPage = '0'
                            } else {
                                reportDb.isPage = '1'
                            }*/
                            reportDb.isPage = '1'
                        } else {
                            reportDb.isPage = '0'
                        }
                        if (this.sqlForm.dbType == "0"){
                            reportDb.dbDynSql = this.sqlForm.dbDynSql;
                        } else {
                            reportDb.apiUrl = this.sqlForm.apiUrl;
                            reportDb.apiMethod = this.sqlForm.apiMethod;
                        }
                        reportDb.fieldList = this.tab1.data; //解析出表字段
                        reportDb.paramList = this.tab2.data; //动态表单参数
                        console.log('报表数据集添加参数',reportDb)
                        $http.post({
                            url: apis.saveDb,
                            contentType:'json',
                            data:JSON.stringify(reportDb),
                            success:(res)=>{
                                this.$emit('saveback',res.id);
                                for(let key in this.sqlForm){
                                    this.sqlForm[key] = "";
                                }
                                this.sqlForm.isPage=false;
                                this.sqlForm.apiMethod="0";
                                this.tab1.data = [];
                                this.tab2.data = [];
                                this.sqlModal = false;
                            }
                        });
                        return;
                    }else{
                        setTimeout(() => {
                            this.loading = false
                            this.$nextTick(() => {
                                this.loading = true
                            })
                        }, 500)
                        return;
                    }
                });
            },
            checkChange(ispage){
                if (ispage){
                    $http.get({url:apis.queryIsPage(excel_config_id),success:(result)=>{
                            if (result){
                                this.$Modal.confirm({
                                    content: '已有数据集分页,是否更改?',
                                    onOk: () => {
                                        this.sqlForm.isPage = true;
                                    },
                                    onCancel: ()=> {
                                        this.sqlForm.isPage = false;
                                    }
                                });
                            }
                        }});
                }
            },
            selectdbSource(val){
                //this.clearSqlForm()
                this.sqlForm.dbSource = val;
                /*加载数据源的表信息*/
                this.handleDbSourceTable()
            },
            clearSqlForm(){
                this.sqlForm.dbDynSql="";
                this.tab1.data = [];
                this.tab2.data = [];
            },
            handleDbSourceTable(){
                this.tableList=[];
                let dbSource = this.sqlForm.dbSource;
                if(!dbSource){
                    return;
                }
                $http.post({
                    url:apis.loadTable,
                    data:{
                        dbSource:dbSource
                    },
                    success:(result)=>{
                        this.tableList=result
                        //存在dbDynSql，就是编辑的状态
                        if(this.sqlForm.dbDynSql){
                            let form_number = this.sqlForm.dbDynSql.indexOf("from ");
                            let where_number = this.sqlForm.dbDynSql.indexOf(" where");
                            let tableName = (where_number>-1?this.sqlForm.dbDynSql.substring(form_number+4,where_number):this.sqlForm.dbDynSql.substring(form_number+4)).trim();
                            this.currentIndex=this.tableList.findIndex((item) => item.Name === tableName);
                            /*this.$nextTick(() => {
                                document.getElementById(this.currentIndex+'').scrollIntoView()
                            })*/
                        }
                    }
                })
            },
            tableNameClick(item,index){
               this.currentIndex=index;
               let sql = "select *  from "+ item.Name;
               this.sqlForm.dbDynSql=sql;
               this.handleSQLAnalyze();
            },
            sourceManage(){
                this.sourceModal = true;
            },
            handleSQLAnalyze() {
                let dbDynSql = this.sqlForm.dbDynSql;
                let dbSource = this.sqlForm.dbSource;
                if(!dbDynSql){
                    return;
                }
                if(dbDynSql.indexOf("where")!=-1){
                    dbDynSql = dbDynSql.substr(0,dbDynSql.indexOf("where"));
                }
                $http.post({
                    url:apis.executeSelectSql,
                    data:{
                        sql:dbDynSql,
                        dbSource:dbSource
                    },
                    success:(result)=>{
                        this.tab1.data = result;
                        this.tab1.data.forEach((item,index)=>{
                            item.tableIndex = index+1;
                        })
                    }
                })
            },
            handleApiAnalyze(){
                let dbDynApi = this.sqlForm.apiUrl;
                if(!dbDynApi){
                    return;
                }
                if(dbDynApi.indexOf("?")!=-1){
                    dbDynApi = dbDynApi.substr(0,dbDynApi.indexOf("?"));
                }
                let apiMethod = this.sqlForm.apiMethod;
                $http.post({
                    url:apis.executeSelectApi,
                    data:{
                        api:dbDynApi,
                        method:apiMethod
                    },
                    success:(result)=>{
                        this.tab1.data = result;
                        this.tab1.data.forEach((item,index)=>{
                            item.tableIndex = index+1;
                        })
                    }
                })
            },
            removeFieldTable(){
                this.deleteFieldModel = true;
            },
            selectFieldAll(){
                this.tab1.selectParamTables = this.tab1.data.map(item=>
                {
                    return {"tableIndex":item.tableIndex,"id":item.id}
                });
            },
            cancelFieldAll(){
                this.tab1.selectParamTables = [];
            },
            selectField(selection,row){
                this.tab1.selectParamTables=[...this.tab1.selectParamTables,{"tableIndex":row.tableIndex,"id":row.id}];
            },
            cancelField(selection,row){
                this.tab1.selectParamTables = this.tab1.selectParamTables.filter(item=>item.tableIndex!=row.tableIndex);
            },
            dbDynSqlBlur(){
                //获得原数据Map
                let dataMap={}
                if(this.tab2.data && this.tab2.data.length>0){
                    this.tab2.data.forEach(item=>{
                        dataMap[item.paramName] = item;
                    })
                }

                let dbDynSql = this.sqlForm.dbDynSql;

                let reg=/\$\{(\S+)\}/g;
                if(!reg.test(dbDynSql)){
                    return;
                }

                let dbDynSqlArr = dbDynSql.match(reg);
                let paramsArr = [];
                if(dbDynSqlArr && dbDynSqlArr.length>0){
                    let maxOrderNum = 1;
                    dbDynSqlArr.forEach((item,index)=>{
                        item = item.replace("\$\{","").replace("\}","").trim();
                        let paramObj = {};
                        paramObj.paramName = item;
                        paramObj.paramTxt = item;
                        paramObj.orderNum = maxOrderNum++;
                        paramObj.tableIndex = paramObj.orderNum;
                        const oldItem =  dataMap[item];
                        paramObj.id = (oldItem && oldItem.id) || "";
                        paramObj.paramValue = (oldItem && oldItem.paramValue) || "";
                        paramsArr.push(paramObj);
                    })
                }
                this.tab2.data = [...paramsArr];
            },
            addParamTable(){
                let indexArr = this.tab2.data.map(item=>item.tableIndex);
                let orderNumArr = this.tab2.data.map(item=>item.orderNum);
                if(indexArr.length==0){
                    indexArr=[0];
                }
                if(orderNumArr.length==0){
                    orderNumArr=[0];
                }
                this.tab2.selectParamTables = [];
                this.tab2.data=[...this.tab2.data,{
                    'paramName':"",
                    'paramTxt':"",
                    'paramValue':"",
                    'orderNum':Math.max(...orderNumArr)+1,
                    'tableIndex':Math.max(...indexArr)+1
                }];
            },
            removeParamTable(){
                this.deleteParamModel = true;
            },
            deleteParamTable(){
                let tableIndexArr = this.tab2.selectParamTables.map(item=>item.tableIndex);
                this.tab2.data = this.tab2.data.filter(item=>!tableIndexArr.includes(item.tableIndex));
                let selectTableObj = this.tab2.selectParamTables.filter(item=>item.id);
                let selectIds = selectTableObj.map(item=>item.id);
                this.tab2.selectParamTables = [];
                let dbDynSql  = this.sqlForm.dbDynSql;
                dbDynSql = dbDynSql.substring(0,dbDynSql.indexOf("where"));
                let paramArr = []
                if(this.tab2.data.length>0){
                    dbDynSql = dbDynSql+" where ";
                    this.tab2.data.forEach(item=>{
                        const paramName = `\$\{item.paramName\}`;
                        paramArr.push(`\$\{paramName\}='`+"\$\{"+paramName+"\}'")
                    })
                }
                dbDynSql = dbDynSql+paramArr.join(" and ");
                this.sqlForm.dbDynSql=dbDynSql.trim();
                const deleParams={selectIds,id:this.sqlForm.id,dbDynSql}
                //后台删除,保存时删除
                if(selectIds.length>0){
                    $http.post({
                        url:apis.deleteParamByIds,
                        contentType:'json',
                        data:JSON.stringify(deleParams),
                        success:(result)=>{
                        }
                    });
                }
            },
            removeFieldTable(){
                this.deleteFieldModel = true;
            },
            deleteFieldTable(){
                let tableIndexArr = this.tab1.selectParamTables.map(item=>item.tableIndex);
                this.tab1.data = this.tab1.data.filter(item=>!tableIndexArr.includes(item.tableIndex));
                let selectTableObj = this.tab1.selectParamTables.filter(item=>item.id);
                let selectIds = selectTableObj.map(item=>item.id);
                this.tab1.selectParamTables = [];
                const deleParams={selectIds}
                if(selectIds.length>0){
                    $http.del({
                        contentType:'json',
                        url:apis.deleteFieldByIds,
                        data:JSON.stringify(deleParams),
                        success:(result)=>{
                        }
                    });
                }
            },
            selectParamAll(){
                this.tab2.selectParamTables = this.tab2.data.map(item=>
                {
                    return {"tableIndex":item.tableIndex,"id":item.id}
                });
            },
            cancelParamAll(){
                this.tab2.selectParamTables = [];
            },
            selectParam(selection,row){
                this.tab2.selectParamTables=[...this.tab2.selectParamTables,{"tableIndex":row.tableIndex,"id":row.id}];
            },
            cancelParam(selection,row){
                this.tab2.selectParamTables = this.tab2.selectParamTables.filter(item=>item.tableIndex!=row.tableIndex);
            },
            saveSourceDb(){
                this.sourceModal = false;
            },
            addDataSource(){
                Object.keys(this.dataSource).map(k=>{
                    this.dataSource[k] = ''
                })
                this.visibleData = true;
            },
            selectdbType(name){
                if (name === "MYSQL5.7"){
                    this.dataSource.dbDriver = "com.mysql.cj.jdbc.Driver";
                    this.dataSource.dbUrl = "jdbc:mysql://127.0.0.1:3306/jeecg-boot?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
                }else if (name === "MYSQL5.5"){
                    this.dataSource.dbDriver = "com.mysql.jdbc.Driver";
                    this.dataSource.dbUrl = "jdbc:mysql://127.0.0.1:3306/jeecg-boot?characterEncoding=UTF-8&useUnicode=true&useSSL=false";
                }else if (name === "ORACLE"){
                    this.dataSource.dbDriver = "oracle.jdbc.OracleDriver";
                    this.dataSource.dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
                }else if (name === "SQLSERVER"){
                    this.dataSource.dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                    this.dataSource.dbUrl = "jdbc:sqlserver://127.0.0.1:1433;SelectMethod=cursor;DatabaseName=jeecgboot";
                }
            },
            clearDbSou(){
                this.$refs.dataSource.resetFields();
                this.initDataSource();
            },
            saveDataSource(){
                this.$refs.dataSource.validate((valid)=>{
                    if(valid){
                        //保存表单
                        let dbSource = {};
                        dbSource.id = this.dataSource.id;
                        dbSource.reportId = excel_config_id;
                        dbSource.code = this.dataSource.code;
                        dbSource.name = this.dataSource.name;
                        dbSource.dbType = this.dataSource.dbType;
                        dbSource.dbDriver = this.dataSource.dbDriver;
                        dbSource.dbUrl = this.dataSource.dbUrl;
                        dbSource.dbUsername = this.dataSource.dbUsername;
                        dbSource.dbPassword = this.dataSource.dbPassword;
                        $http.post({
                            contentType:'json',
                            url: apis.addDataSource,
                            data:JSON.stringify(dbSource),
                            success:(result)=>{
                                this.initDataSource();
                                this.dataSource = {};
                                this.visibleData = false;
                            }
                        });
                        return;
                    }else{
                        setTimeout(() => {
                            this.loading = false
                            this.$nextTick(() => {
                                this.loading = true
                            })
                        }, 500)
                        return;
                    }
                })
            },
            initDataSource(){
                $http.get({url:apis.initDataSource,success:(result)=>{
                        let reportResult = result;
                        if(!reportResult){
                            return;
                        }
                        console.log("result",result)
                        this.sourceTab.data = reportResult;
                        this.sourceTab.data.forEach((item,index)=>{
                            item.tableIndex = index+1;
                        })
                    }});
            },
            renderButton(h, params) {
                return h('div',[
                    h('i-button', {
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        style:{
                            'margin-right':'5px'
                        },
                        on: {
                            click: () => {
                                this.sourceTab.data.forEach((item)=>{
                                    if (item.id === params.row.id){
                                        this.dataSource = item;
                                    }
                                })
                                this.visibleData = true;
                            }
                        }
                    },'编辑'),
                    h('i-button', {
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        on: {
                            click: () => {
                                this.$Modal.confirm({
                                    title:"提示",
                                    content: '是否确认删除?',
                                    onOk: () => {
                                        let dbSource = {};
                                        dbSource.id = params.row.id;
                                        $http.post({
                                            contentType:'json',
                                            url: apis.delDataSource,
                                            data:JSON.stringify(dbSource),
                                            success:(result)=>{
                                                this.$Notice.success({
                                                    title: '删除成功'
                                                });
                                                this.initDataSource();
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    },'删除')
                ])
            },
            renderInput(h, params, field,tabIndex) {
                return h('i-input', {
                    props: {
                        "size":"small",
                        type: 'text',
                        value: this[tabIndex].data[params.index][field],
                        placeholder: `请输入`+params.column.title
                    },
                    on: {
                        'on-blur': (event) => {
                            if(tabIndex==="tab2"){
                                let tableIndexArr = vm.tab2.selectParamTables.map(item=>item.tableIndex);
                                vm.tab2.data.forEach(item=>{
                                    if(tableIndexArr.includes(item.tableIndex)){
                                        item._checked = true;
                                    }
                                });
                            }
                            this[tabIndex].data[params.index][field] = event.target.value;
                        }
                    },
                })
            },
            dataSourceTest(){
                let dbSource = {};
                dbSource.dbType = this.dataSource.dbType;
                dbSource.dbDriver = this.dataSource.dbDriver;
                dbSource.dbUrl = this.dataSource.dbUrl;
                dbSource.dbName = this.dataSource.dbName;
                dbSource.dbUsername = this.dataSource.dbUsername;
                dbSource.dbPassword = this.dataSource.dbPassword;
                $http.post({
                    contentType:'json',
                    url: apis.testConnection,
                    data:JSON.stringify(dbSource)
                });
            }
        }
    })
</script>