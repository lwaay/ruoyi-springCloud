const reg = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
vm = new Vue({
        el: "#app",
        created(){
           this.createLoading = true
            this.offsetInfo = '0,0'
            this.activedTreeNode = ''
            this.windowHeight = window.innerHeight

            //let abc = document.createElement('img')
            //abc.src = 'http://localhost:8080/jeecg-boot/excel_online/bg_1606295179160.png'
            //this.abc = abc
        },
        mounted() {
            // this.tableHeight = window.innerHeight - this.$refs.dynamicTable.$el.offsetTop - 200;
            // this.paramTableHeight = window.innerHeight - this.$refs.paramTable.$el.offsetTop - 200;
          // this.$refs.dynamicTable.$el.style.cssText= "padding-bottom: 10%;";
            this.token = window.localStorage.getItem('JmReport-Access-Token');
            console.log("index_design--------------",this.token);
            this.initFieldTree();
            //多数据源
            //this.initDataSource();
            //图表选择弹框样式添加
            addChartModalSelectedStyle();
        },
        data: function () {
            let _this = this;
            return {
                windowHeight:'',
                actionUrlPre: window.location.origin+baseFull,
                createLoading: false,
                treeObj:{},//数据集code和名称
                dataXs:"",
               /* menuitem : "printinfo",*/
                chartOptions:{},
                menuitem : "printinfo",
                menuList:[{value: 'printinfo',label: '打印设计'},{value: 'datainfo',label: '数据报表'}, {value: 'chartinfo', label: '图形报表'},],
                polyWayList:[{value: 'select',label: '列表'},{value: 'group',label: '分组'}],
                directionList:[{value: 'down',label: '纵向'},{value: 'right',label: '横向'}],
                /*dataSourceTypeList:[{value: 'MYSQL5.5',label: 'MySQL5.5'},{value: 'MYSQL5.7',label: 'MySQL5.7'},{value: 'ORACLE',label: 'Oracle'},{value: 'SQLSERVER',label: 'SQLServer'}],
                */designerObj:{
                    id:"",
                    name:"",
                    type:"printinfo"
                },
                visible: false,
                token:"",
                apiModal:false,
                deleteParamModel:false,
                deleteFieldModel:false,
                dataShow:true, //数据显示/隐藏
                propsContentShow:true, //属性显示/隐藏
                tabStyleShow:false,//右侧样式设置显示隐藏
                tabDataShow:false,//右侧数据显示隐藏
                tabValue:"1", //
                treeSpanNum:3,
                tableSpanNum:21,
                propsSpanNum:3,
                excel:{
                    excelValue:"",
                    type:"text",
                    coordinate:"",  //坐标
                    ri:0,
                    ci:0,
                    polyWay:"",
                    direction:"",
                    isDict: 0,
                    dictCode:''
                },
                offsetInfo: '0,0',
                loading:true,
                tableHeight: 0,
                paramTableHeight: 0,
              /*  moduleTitle: "",*/
                //addIsPage: false,
               /* tab1: {
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
                            /!*width: '220',*!/
                            render: (h, params) => {
                                return this.renderInput(h, params, 'fieldName','tab1')
                            }
                        },
                        {
                            title: '排序',
                            key: 'orderNum',
                            /!*width: '80',*!/
                            render: (h, params) => {
                                return this.renderInput(h, params, 'orderNum','tab1')
                            }
                        },
                        {
                            title: '字段文本',
                            /!*width: '220',*!/
                            key: 'fieldText',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'fieldText','tab1')
                            }
                        },
                        {
                            title: '类型',
                            /!*width: '140',*!/
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
                            /!*width: '220',*!/
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
                            /!*width: '140',*!/
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
                        /!*{
                            title: '是否显示',
                            width: '100',
                            key: 'isShow',
                            render:(h, params)=> {
                                return h('Checkbox',{
                                    props: {
                                        size:'small',
                                        value: this.tab1.data[params.index].isShow,
                                    },
                                    on: {
                                        'on-change': (value) => {
                                            _this.tab1.data[params.index].isShow = value;
                                        }
                                    }
                                });
                            }
                        },
                        {
                            title: '字段href',
                            width: '220',
                            key: 'fieldHref',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'fieldHref','tab1')
                            }
                        },
                        {
                            title: '查询模式',
                            width: '140',
                            key: 'searchMode',
                            render: (h, params) => {
                                let options = [ // 下拉选项
                                    {title: '单条件查询', value: 'single'},
                                    {title: '范围查询', value: 'group'}
                                ];

                                return h('i-select', {
                                        props: {
                                            size:'small',
                                            value: '',
                                        },
                                        on: {
                                            'on-change': (value) => {
                                                _this.tab1.data[params.index].fieldType = value;
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
                            title: '取值表达式',
                            width: '220',
                            key: 'replaceVal',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'replaceVal','tab1')
                            }
                        },
                        {
                            title: '字典code',
                            width: '220',
                            key: 'dictCode',
                            render: (h, params) => {
                                return this.renderInput(h, params, 'dictCode','tab1')
                            }
                        },
                        {
                            title: '是否查询',
                            width: '100',
                            key: 'isSearch',
                            render:(h, params)=> {
                                return h('Checkbox',{
                                    props: {
                                        size:'small',
                                        value: this.tab1.data[params.index].isSearch,
                                    },
                                    on: {
                                        'on-change': (value) => {
                                            _this.tab1.data[params.index].isSearch = value;
                                        }
                                    }
                                });
                            }
                        },*!/

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
                sqlModal: false,*/
                treeData:null,
               /* sqlFormValidate:{
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
                },*/
                visibleData: false,
                /*dataSource: {
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
                    ],
                },*/
                //自定义颜色名称
                customColorNameList:[],
                //数值显示位置集合
                labelPositionArray:[],
                //数值设置 series中的label配置信息
                seriesLabelSettings:{},
                //象形柱图设置
                pictorialSettings:{},
                //漏斗设置
                funnelSettings:{},
                //边距设置
                marginSettings:{},
                //中心点设置
                centralPointSettings:{},
                //饼块设置 饼状图专有
                pieSettings:{},
                //线条设置 折线图专有
                lineSettings:{},
                //柱体设置 柱状图专有
                barSettings:{},
                //散点设置 散点图专有
                scatterSettings:{},
                //仪表图设置 仪表图专有
                gaugeSettings:{},
                //关系图设置 关系图专有
                graphSettings:{},
                //地图设置 地图专有
                mapGeoSettings :{},
                //雷达图设置 雷达图专有
                radarSettings :[],
                //图表grid配置 s
                gridSettings:{},
                //图表坐标轴配置 s
                xAxisSettings:{},
                yAxisSettings:'',
                doubleyAxisSettings:[],
                //图表标题配置 c
                titleSettings:{},
                //图表标题配置 c
                legendSettings:{},
                //图表提示语配置 c
                tooltipSettings:{},
                //数据集配置
                dataSettings: {
                    dataType:'api',
                    apiStatus:'',
                    apiUrl:'',
                    dataId:'',
                    axisX:'',
                    axisY:'',
                    series:'',
                    yText:'',
                    xText:'',
                    dbCode:[],
                    dataId1:'',
                    source:'',
                    target:'',
                    isTiming:false,
                    intervalTime:"5",
                },
                //api静态数据
                apiStaticDataList:'',
                //sql数据集
                sqlDataList:[],
                //sql数据集字段
                sqlDataFieldList:[],
                //sql数据集字段2
                sqlDataFieldList2:[],
                //api数据集
                apiDataList:[],
                // 展开节点的id
                activedTreeNode:'',
                //背景图设置
                backgroundImg:[{'name': 'backgroundImg','url': '' }],
                backgroundSettingShow:false,
                backgroundSettings:{
                    path: '',
                    repeat: '',
                    width:'',
                    height:''
                },
                //图表背景图设置
                chartBackgroundImg:[{'name': 'chartBackgroundImg','url': '' }],
                chartBackground:{
                    color: '#fff',
                    enabled: false,
                    image: '',
                    repeat: 'repeat'
                },
                //条形码设置
                barcodeSettings: false,
                //二维码设置
                qrcodeSettings: false,
                echartInfo:{
                    id: '',
                    //标题
                    titleShow: true,
                    titleText: '',
                    titleFontSize: 20,
                    titleFontWeight: 'bolder',
                    titleColor: '#c43632',
                    titleLocation: 'left',
                    //柱体
                    barWidth: 0,
                    barRadius: 0,
                    barMinHeight: 0,
                    //折线
                    step: false, //阶梯线图
                    showSymbol: true,//标记点
                    smooth: false,//平滑曲线
                    symbolSize: 8,// 设置折线上圆点大小
                    linewidth: 5,// 设置线宽
                    //饼图
                    pieLabelPosition:'outside', //标签位置
                    minAngle:0,  //最小角度
                    notCount:false,  //不展示零
                    autoSort:false,  //自动排序
                    roseType:false,  //南丁格尔玫瑰
                    isRadius:false,  //是否环形
                    pieRadius:'40%,50%',//半径大小
                    //地图
                    scale :0,
                    numerTextHighCol:'',
                    borderWidth:0,
                    areaCol:'',
                    areaHighCol:'',
                    borderCol:'',
                    //X轴样式
                    xaxisShow: true,
                    xaxisText: "",
                    xaxisLine: true,
                    xaxisLinecol: '#c43632',
                    xaxisTextsize: 0,
                    axisLabelRotate:0,
                    //Y轴样式
                    yaxisShow: true,
                    yaxisText: "",
                    yaxisLine: true,
                    yaxisLinecol: '#c43632',
                    yaxisTextsize: 0,
                    //数值设置
                    numerShow: true,
                    numerTextSize: 0,
                    numerTextcol: '#c43632',
                    numerTextweig: "",
                    //提示框
                    tooltipShow: true,
                    tooltipTextSize: 0,
                    tooltipTextcol: '#c43632',
                    //图例
                    legendShow: true,
                    //legendItemWidth: 0,
                    legendTop: "top",
                    legendLeft: "left",
                    legendOrient: "horizontal",
                    legendTextcol: '#c43632',
                    legendTextsize: 0,
                    //图例数据
                    legendData:[],
                    //轴边距
                    gridLeft: 0,
                    gridTop: 0,
                    gridRight: 0,
                    gridBottom: 0,
                    //xy轴字体颜色/轴线颜色
                    axisLabelTextCol: '#c43632',
                    axisLineLineCol: '#c43632',
                    seriesItemNorCol: '#c43632',
                    seriesLinemNorCol: '#c43632',
                },
                dataAllocation:{
                    dataType: 'sqlechData',
                    status: 'staticData',
                    optionData:'{}',
                    selectOptionData: {},
                    apiListOpt:[],
                    sqlListOpt:[],
                    sqlListField:[],
                    sqlxAxis:'',
                    sqlseries:'',
                    sqlgroup:'',//系列属性
                    sqltype:'',//系列类型
                    selectId:'',
                    seriesTypeData:[]//系列类型数据
                },
                addEchart:false,
                chartModule:false,
                chartsType:'',
                selectedChartId :"",//图表选择Id
                selectedChartType :"",//图表选择类型
                //自定义颜色样式
                colorMatchData:[],
                colorMatchModal:false,
                colorMatch:{name:"",color:"",edgeColor:"",opacity:1,lineColor:''},
                //renderChartBackgroudTip:
                chartsflag:false,
                isMultiChart:false,//是否多组
                rightTabName:'name1',
                chartsTypeArr:[],
                seriesTypeData:[],//系列类型数据
                specialChartType:"",
                seriesModal:false,
                tabDisabled:false,
                seriesObj : {},
                seriesColumns: [
                    {
                        title: '系列类型',
                        key: 'name',
                        align:'left',
                        width :110,
                        render: (h, params) => {
                            return this.renderColorButton(h, params,'name',2);
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
                        align:'right',
                        width :80,
                        render: (h, params) => {
                            return this.renderColorButton(h, params,'action',2);
                        }
                    }
                ]
            }
        },
        computed: {
            menuitemClasses: function () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            },
            colorLabel:function(){
                return this.selectedChartType.indexOf('scatter.bubble')!==-1?'中心颜色':'颜色'
            },
            centerDivClass: function () {
                let str = 'jm-rp-designer'
                if(!this.dataShow && !this.propsContentShow){
                    str+=' all'
                }else if(!this.dataShow){
                    str+=' left'
                }else if(!this.propsContentShow){
                    str+=' right'
                }
                return str;
            }
        },
        methods: {
            //百分数格式化
            percentFormat (val) {
                return val + '%';
            },
            //通过百分数转化 获取实际数字
            getNumberFromPercent(p,suffix=''){
                if(!p){
                    return 0;
                }
                return Number((p+'').replace('%','').replace(suffix,''))
            },
            //slider 百分数改变事件
            onPercentChange(value, key, eventName,suffix=''){
                let arr = key.split('.')
                if(arr.length>1){
                    let temp = this
                    for(let i=0;i<arr.length-1;i++){
                        temp = temp[arr[i]]
                    }
                    temp[arr[arr.length-1]] = value+'%'+suffix
                }else{
                    this[key] = value+'%'+suffix
                }
                this[eventName]();
            },
            //校验数据集编码
            validateCodeExist(rule, value, callback){
                if(this.sqlForm.id){
                    callback();
                }
                if(!reg.test(value)){
                    callback(new Error('编码只能包含中英文、数字和下划线'));
                }
                $jm.dataCodeExist(excel_config_id, value, (result)=> {
                    if(result === true){
                        callback('编码已存在!');
                    }else{
                        callback();
                    }
                })
            },
            //自定义系列类型
            addSeriesType(){
                let obj={...this.seriesObj};
                if(obj._index>=0){
                    this.seriesTypeData.splice(obj._index,1,obj);
                }else{
                    this.seriesTypeData.push(obj)
                }
                console.info("addSeriesType",this.seriesTypeData)
                //运行到图表
                this.runChart();
                this.seriesObj={name:'',type:''};
                this.seriesModal=false;
            },
            //设置选择图表类型
            setSelectCharType(item){
                if(item.allowed){
                    this.selectedChartType=item.type;
                    this.selectedChartId=item.id;
                }
            },
            tabClick(name) {
                console.log(this.$refs.dynamicTable.$el.offsetHeight);
                console.log(this.$refs.paramTable.$el.offsetHeight);
                console.log("====tabClick",name);
            },
            selectParamAll(){
                this.tab2.selectParamTables = this.tab2.data.map(item=>
                    {
                       return {"tableIndex":item.tableIndex,"id":item.id}
                    });
            },
          /*  cancelParamAll(){
               this.tab2.selectParamTables = [];
            },
            selectParam(selection,row){
                this.tab2.selectParamTables=[...this.tab2.selectParamTables,{"tableIndex":row.tableIndex,"id":row.id}];
            },
            cancelParam(selection,row){
                this.tab2.selectParamTables = this.tab2.selectParamTables.filter(item=>item.tableIndex!=row.tableIndex);
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

                let reg=/\${(\S+)\}/g;
                let dbDynSql = this.sqlForm.dbDynSql;
                if(!reg.test(dbDynSql)){
                    return;
                }

                let dbDynSqlArr = dbDynSql.match(reg);
                let paramsArr = [];
                if(dbDynSqlArr && dbDynSqlArr.length>0){
                    let maxOrderNum = 1;
                    dbDynSqlArr.forEach((item,index)=>{
                        item = item.replace("${","").replace("}","").trim();
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
            },*/
            toggleLeft(){
                let $i = $($("#treeDiv").find("i")[0]);
                let l1=12.5,l2=12.5;
                let $dataDiv = $("#dataDiv");
                if($i.hasClass("ivu-icon-ios-arrow-back")){
                    //收缩
                    this.dataShow = false
                    //this.treeSpanNum = 1;
                    l1 = 1.5;//收缩后的百分比
                    $i.removeClass("ivu-icon-ios-arrow-back");
                    $i.addClass('ivu-icon-ios-arrow-forward');
                    $dataDiv.slideUp();
                }else{
                    this.dataShow = true
                    //this.treeSpanNum = 3;
                    l1 = 12.5;//展开后的百分比
                    $i.addClass("ivu-icon-ios-arrow-back");
                    $i.removeClass('ivu-icon-ios-arrow-forward');
                    $dataDiv.slideDown();
                }
                if (this.propsContentShow){//判断右边是否展开
                    l2 = 12.5
                } else {
                    l2= 1.5
                }
                let treeDiv = document.getElementById("treeDiv").parentNode;//获取父节点
                treeDiv.style.cssText= "display: block;width: "+ l1 +"%;";//给父节点设置属性
            },
            toggleRight(){
              //propsDivd
                let $i = $($("#propsDiv").find("i")[0]);
                let l1=12.5,l2=12.5;
                let $propsContentDiv = $("#propsContentDiv");
                if($i.hasClass("ivu-icon-ios-arrow-back")){
                    this.propsContentShow = true
                    //this.propsSpanNum = 3;
                    l2=12.5;
                    $i.removeClass("ivu-icon-ios-arrow-back");
                    $i.addClass('ivu-icon-ios-arrow-forward');
                    $propsContentDiv.slideDown();
                }else{
                    //收缩
                    this.propsContentShow = false
                    //this.propsSpanNum = 1;
                    l2 = 1.5;
                    $i.addClass("ivu-icon-ios-arrow-back");
                    $i.removeClass('ivu-icon-ios-arrow-forward');
                    $propsContentDiv.slideUp();
                }
                if (this.dataShow){
                    l1 = 12.5
                } else {
                    l1= 1.5
                }
                //let treeDiv = document.getElementById("propsDiv").parentNode;
                //treeDiv.style.cssText= "display: block;width: "+ l2 +"%;";

               // let l3 = 100 - (l1+ l2);//算出中间table的百分比
                //let treeDiv = document.getElementById("tableDiv").parentNode;
                //treeDiv.style.cssText= "display: block;width: "+ l3 +"%;";
            },
            /*addParamTable(){
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
            },*/
            removeParamTable(){
                this.deleteParamModel = true;
            },
            removeFieldTable(){
                this.deleteFieldModel = true;
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
                        const paramName = `${item.paramName}`;
                        paramArr.push(`${paramName}='`+"${"+paramName+"}'")
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
            submitValue(e){
               let value = e.target.value;
               setExcelData({ri:this.excel.ri,ci:this.excel.ci},value);
            },
            initFieldTree(){
                console.log("index_initFieldTree--------------",token);
                if (excel_config_id != null && excel_config_id != ""){
                    $http.get({url:apis.fieldTreeUrl(excel_config_id),success:(result)=>{
                        console.log("initTree======");
                        const treeObj = {};
                        let treeResult = result;
                        this.apiDataList = [];
                        this.sqlDataList = [];
                        result.forEach(item=>{
                            if (item[0].type === "0"){
                                this.sqlDataList.push(item[0]);
                            }else {
                                this.apiDataList.push(item[0]);
                            }
                        })
                        if(treeResult && treeResult.length>0){
                            for(let node of treeResult){
                                if(this.activedTreeNode == node[0].dbId){
                                    node[0].expand = true
                                }else{
                                    node[0].expand = false
                                }
                            }
                            if(!this.activedTreeNode){
                                treeResult[0][0].expand = true
                            }
                            treeResult.forEach(item=>{
                                treeObj[item[0].title] = item[0].code;
                                item[0].render=(h, { root, node, data })=>{
                                    addDrag();
                                    return h('div', {
                                        style: {
                                            display: 'flex',
                                            width: '100%',
                                            alignItems: 'end',
                                            padding: '0 0 0 4px',
                                            background: data.expand===true?'#d5e8fc':'none'
                                        }
                                    }, [
                                        h('div', [
                                            h('div',{style:{display: 'inline-block', width: '90px',overflow: 'hidden',textOverflow: 'ellipsis',whiteSpace: 'nowrap'}}, data.title)
                                        ]),
                                        h('div', [
                                            h('i-button', {
                                                props: Object.assign({}, {
                                                    size: 'small',
                                                    type:"text"
                                                }, {
                                                    icon: 'md-create',
                                                }),
                                                on: {
                                                    "click": () => {
                                                        this.$refs.dataSource.editById(data.dbId)
                                                    }
                                                }
                                            }),
                                            h('i-button', {
                                                props: Object.assign({}, {
                                                    size: 'small',
                                                    type:"text"
                                                }, {
                                                    icon: 'md-close',
                                                }),
                                                on: {
                                                    "click": () => {
                                                        this.$Modal.confirm({
                                                            title:'提示',
                                                            render: (h) => {
                                                                return h('div',{style:{ margin: '0 -15px'}},
                                                                    [
                                                                        h('div',{class:'modal-body-del'},'是否确认删除?'),
                                                                    ])
                                                            },
                                                            /*content: '<i class="ivu-icon ivu-icon-ios-alert" style="color: #f90;font-size: 20px;margin-right: 5px;"></i>是否确认删除?',*/
                                                            onOk: () => {
                                                                $http.get({url:apis.delDbData(data.dbId),success:(result)=>{
                                                                    console.log('result=====',result);
                                                                    this.$Notice.success({
                                                                        title: '删除成功'
                                                                    });
                                                                    this.initFieldTree();
                                                                }});
                                                            }
                                                        });
                                                    }
                                                }
                                            })
                                        ])
                                    ]);
                                }
                                //渲染子数据
                                item[0].children.forEach(item=>{
                                    item.render=(h, { root, node, data })=>{
                                        return h('Tooltip', {props: {content: data.title }},[ h('span', data.fieldText)])
                                    }
                                });
                            })
                        }
                        this.treeData = treeResult;
                        this.treeObj = treeObj;
                    }});
                    this.getReport();
                    this.createLoading = false
                }
            },
            getReport(){
                $http.get({url:apis.getReport(excel_config_id),success:(result)=>{
                    if (result){
                        this.designerObj = result;
                    }
                }});
            },
            /*checkChange(ispage){
                if (ispage){
                    $http.get({url:api.queryIsPage(excel_config_id),success:(result)=>{
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
            getDbField(){
               let dbFields = [];
               this.tab1.data.forEach(item=>{
                  let dbField = {};
                   dbField.fieldName = item.fieldName;
                   dbField.fieldText = item.fieldTxt;
                  dbFields.push(dbField);
               });
               return dbFields;
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
            },*/
            saveDbBack(id){
                this.activedTreeNode = id
                this.initFieldTree();
            },
            cancelback(obj){
                console.log("obj===>",obj)
                this.designerObj = obj;
            },
            /*handleSQLAnalyze() {
                let dbDynSql = this.sqlForm.dbDynSql;
                let dbSource = this.sqlForm.dbSource;
                if(!dbDynSql){
                    return;
                }
                if(dbDynSql.indexOf("where")!=-1){
                    dbDynSql = dbDynSql.substr(0,dbDynSql.indexOf("where"));
                }
                $http.post({
                    url:api.executeSelectSql,
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
            selectdbSource(val){
                this.sqlForm.dbSource = val;
            },*/
            selectmenuList(val){
                this.designerObj.type = val;
            },
            /*handleApiAnalyze(){
                let dbDynApi = this.sqlForm.apiUrl;
                if(!dbDynApi){
                    return;
                }
                if(dbDynApi.indexOf("?")!=-1){
                    dbDynApi = dbDynApi.substr(0,dbDynApi.indexOf("?"));
                }
                let apiMethod = this.sqlForm.apiMethod;
                $http.post({
                    url:api.executeSelectApi,
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
            },*/
            //渲染input
            /*renderInput(h, params, field,tabIndex) {
                return h('i-input', {
                    props: {
                        "size":"small",
                        type: 'text',
                        value: this[tabIndex].data[params.index][field],
                        placeholder: `请输入${params.column.title}`
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
            },*/
            changeTree(value){
                console.log("changeTree",value);
            },
            onTreeToggleExpand(node){
                if(node.expand===true){
                    this.activedTreeNode = node.dbId
                }
            },
            /*initDataSource(){
                $http.get({url:api.initDataSource,success:(result)=>{
                    //this.$Spin.hide();
                    this.createLoading = false
                    let reportResult = result;
                    if(!reportResult){
                        return;
                    }
                    this.sourceTab.data = reportResult;
                    this.sourceTab.data.forEach((item,index)=>{
                        item.tableIndex = index+1;
                    })
                }});
            },*/
            savePopup(){
                var that = this;
                let creatFlag = that.excelQueryName();
                if (creatFlag){
                    let data=that.dataXs;
                    const saveFn = (obj) => {
                        //判断报表是否有分组字段
                        const rows = data.rows;
                        let groupField = "";
                        for (let key in rows) {
                            if (groupField) break;
                            const cells = rows[key].cells;
                            for (let cellKey in cells) {
                                const text = cells[cellKey].text;
                                if (!text) continue;
                                if (text.includes(".group(")){
                                    groupField = text.replace("group(", "").replace(")}", "").replace("#\{", "")
                                    break;
                                }
                            }
                        }
                        if (groupField){
                            data.isGroup = true;
                            data.groupField = groupField;
                        }
                        if (data.chartList && data.chartList.length > 0){
                            data.chartList.forEach(item => {
                                if (item.width === 0 || item.height === 0){
                                    item.width = "650";
                                    item.height = "350";
                                }
                            })
                        }
                        var dataStr = JSON.stringify({designerObj: obj, ...data});
                        if (obj.name != null && obj.name != ""){
                            $http.post({
                                contentType:'json',
                                url: apis.saveReport,
                                data: dataStr,
                                success:(result)=>{
                                    xs.tip("保存成功!");
                                    onbeforeunload="return true";
                                    window.location.reload();
                                },
                            })
                        }
                    }
                    vm.handleCreate(saveFn, data);
                    that.visible = false;
                }
            },
            handleCreate(cb,data){
                var that = this;
                var obj = that.designerObj;
                if (obj.name == "" || obj.name == null) {
                    that.visible = true;
                    that.dataXs = data;
                    //that.$Message.error("名称不能为空!");
                    return "";
                }
                //同步校验
                let creatFlag = that.excelQueryName();
                if (creatFlag){
                    obj.id = excel_config_id;
                    cb(obj);
                }
            },
            excelQueryName () {
                var creatFlag = true;
                var that = this;
                var obj = that.designerObj;
                if(obj.name != "" && obj.name != null){
                    $http.post({
                        contentType:'json',
                        url: apis.excelQueryName,
                        data:JSON.stringify(obj),
                        fail:(result)=>{
                            obj.name="";
                            creatFlag = false;
                        }
                    });
                }
                return creatFlag;
            },
            changeName : function () {
            },
            onMenuSelect:function(name){
                this.$refs.dataSource.onMenuSelect(name)
            },
            selectPolyList(name){
                //下拉改变单元格值
                let data = xs.data.getSelectArea();
                if(data.sri<0 || data.sci<0) return;
                if(!xs.data.rows["_"][data.sri]) return;
                if(!xs.data.rows["_"][data.sri].cells[data.sci]) return;
                let textr = xs.data.rows["_"][data.sri].cells[data.sci].text;
                if (textr != "" && textr.indexOf("#{") != -1){
                    if (name === 'group'){
                        if (xs.data.rows["_"][data.sri].cells[data.sci].direction === 'right') return;
                        xs.data.rows["_"][data.sri].cells[data.sci].aggregate='group';
                        let text = xs.data.rows["_"][data.sri].cells[data.sci].text;
                        let newxsdata = text.replace(subStringStr(text,"#{","}").split(".")[1],"group("+subStringStr(text,"#{","}").split(".")[1]+")");
                        xsSetNewdata(data,newxsdata)
                    }else if(name === 'select'){
                        if (xs.data.rows["_"][data.sri].cells[data.sci].direction === 'right') return;
                        xs.data.rows["_"][data.sri].cells[data.sci].aggregate='select';
                        let text = xs.data.rows["_"][data.sri].cells[data.sci].text;
                        let subtext = subStringStr(text,"#{","}").split(".")[1];
                        let newxsdata = text.replace(subtext,subStringStr(subtext,"group(",")"));
                        xsSetNewdata(data,newxsdata)
                    }
                }
            },
            selectDirectionList(name){
                //下拉改变单元格值
                let data = xs.data.getSelectArea();
                if(data.sri<0 || data.sci<0) return;
                if(!xs.data.rows["_"][data.sri]) return;
                if(!xs.data.rows["_"][data.sri].cells[data.sci]) return;
                let textr = xs.data.rows["_"][data.sri].cells[data.sci].text;
                if (textr != "" && textr.indexOf("#{") != -1){
                    if (name === 'right'){
                        xs.data.rows["_"][data.sri].cells[data.sci].direction='right';
                        let text = xs.data.rows["_"][data.sri].cells[data.sci].text;
                        let text2 = subStringStr(text,"#{","}").split(".")[1];
                        let text4 = "";
                        if (text2.indexOf("group(") != -1) {
                            text4 = subStringStr(text2, "group(", ")");
                        }else {
                            text4 = text2;
                        }
                        let text3 = "groupRight("+text4+")"
                        let newxsdata = text.replace(text2,text3);
                        xsSetNewdata(data,newxsdata)
                        this.excel.polyWay = 'group';
                        this.$refs.excelPolyWay.disabled = true;
                    }else if(name === 'down'){
                        xs.data.rows["_"][data.sri].cells[data.sci].direction='down';
                        let text = xs.data.rows["_"][data.sri].cells[data.sci].text;
                        let subtext = subStringStr(text,"#{","}").split(".")[1];
                        let newxsdata = text.replace(subtext,subStringStr(subtext,"groupRight(",")"));
                        xsSetNewdata(data,newxsdata)
                        this.excel.polyWay = 'select';
                        this.$refs.excelPolyWay.disabled = false;
                    }
                }
            },
            // 是否使用字典下拉框改变事件
            selectUseDict(value){
                //下拉改变单元格值
                if(!value && value!=0){
                    return ;
                }
                let data = xs.data.getSelectArea();
                if(data.sri<0 || data.sci<0){
                    return;
                }
                if(value==0){
                    let code = vm.excel.dictCode
                    this.removeReportDictCode(code)
                    vm.excel.dictCode = ''
                    xs.data.rows["_"][data.sri].cells[data.sci]['dictCode']=''
                }
                xs.data.rows["_"][data.sri].cells[data.sci]['isDict']=value
                //xs.sheet.reload()
            },
            // 字典编码改变事件
            changeDictCode(){
                let value = this.excel.dictCode
                console.log('changeDictCode',value)
                let data = xs.data.getSelectArea();
                if(data.sri<0 || data.sci<0) return;
                console.log('changeDictCode', value)
                if(vm.excel.isDict==1){
                    xs.data.rows["_"][data.sri].cells[data.sci]['dictCode']=value
                    this.addReportDictCode(value)
                }
            },
            //新增字典编码到excel data dicts
            addReportDictCode(code){
                if(!xs.data.dicts){
                    xs.data.dicts = []
                }
                xs.data.dicts.push(code)
            },
            //移除字典编码
            removeReportDictCode(code){
                if(xs.data.dicts){
                    let index = xs.data.dicts.indexOf(code)
                    if(index>=0){
                        xs.data.dicts.splice(index,1);
                    }
                }
            },
            sourceManage(){
                this.sourceModal = true;
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
            /*selectdbType(name){
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
                            url: api.addDataSource,
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
            //渲染button
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
                                            url: api.delDataSource,
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
            },*/
            //渲染button
            renderColorButton(h, params,key,type) {
                console.log('params',params)
                if(key=='action'){
                    return h('div',{style:{display:'flex'}},[
                            h('i-button', {
                                props: Object.assign({}, {
                                    type: 'default',
                                    size: 'small',
                                    type:"text"
                                }, {
                                    icon: 'md-create',
                                }),
                                on: {
                                    "click": () => {
                                        this.seriesObj=params.row;
                                        this.seriesModal = true;
                                    }
                                }
                            }),
                        h('i-button', {
                            props: Object.assign({}, {
                                size: 'small',
                                type:"text"
                            }, {
                                icon: 'md-close',
                            }),
                            on: {
                                click: () => {
                                    this.$Modal.confirm({
                                        title:'提示',
                                        content: '是否确认删除?',
                                        onOk: () => {
                                          this.seriesTypeData.splice(params.index, 1);
                                          this.runChart();
                                        }
                                    });
                                }
                            }
                        })
                    ])
                }else{
                    //行数据显示渲染和编辑操作
                    return h('div',{
                        style:{
                            display: 'flex',
                            width: '100px',
                            alignItems: 'center'
                          }
                       },
                      [
                     (type === 1)? h('div',{style:{'background':params.row.color,'width':'15px', 'height':'15px'}}): h('div',{style:{}}, params.row.type),
                      h('div', [
                          h('div',{style:{display: 'inherit',width:  type === 1? '75px': '60px',overflow: 'hidden',textOverflow: 'ellipsis',whiteSpace: 'nowrap'}}, '\xa0'+params.row.name)
                      ])
                    ])
                }
            },
           /* dataSourceTest(){
                let dbSource = {};
                dbSource.dbType = this.dataSource.dbType;
                dbSource.dbDriver = this.dataSource.dbDriver;
                dbSource.dbUrl = this.dataSource.dbUrl;
                dbSource.dbName = this.dataSource.dbName;
                dbSource.dbUsername = this.dataSource.dbUsername;
                dbSource.dbPassword = this.dataSource.dbPassword;
                $http.post({
                    contentType:'json',
                    url: api.testConnection,
                    data:JSON.stringify(dbSource)
                });
            },*/
            //图表标题
            echTilChange(status){
                this.echartInfo.titleShow = status;
            },
            //图表静态数据编辑框
            addEchartInfoData(){
                this.addEchart = true;
            },
            addEchartData(){
                this.addEchart = false;
                let str = this.apiStaticDataList
                let settings = Object.assign(this.dataSettings,{run: 1, axisX: 'name', axisY: 'value', series: 'type'})
                refreshChart(this.echartInfo.id, this.chartOptions, settings, JSON.parse(str))
            },
            //选择图表sql
            selectSqlListOpt(name){
                this.dataAllocation.sqlxAxis = '';
                this.dataAllocation.sqlseries = '';
                this.dataAllocation.sqlgroup = '';
                this.dataAllocation.sqltype = '';
                this.dataAllocation.seriesTypeData =[];
                this.dataAllocation.sqlListOpt.forEach(item=>{
                    if (item.dbId === name){
                        this.dataAllocation.sqlListField = item.children;
                    }
                });
            },
            changeLayerOffset(){
                let value = this.offsetInfo
                if(!value){
                    xs.updateLayerOffset(this.echartInfo.id, [0,0]);
                    return;
                }
                if(value.indexOf(',')<0){
                    this.$Message.warning('偏移量格式不正确！');
                    return;
                }
                let arr = value.split(',')
                if(arr.length!=2){
                    this.$Message.warning('偏移量格式不正确！');
                    return;
                }
                xs.updateLayerOffset(this.echartInfo.id, arr);
            },
            //单击选中图表
            selectChart(data){
                //this.clearRightTabpane();
                if(!data){
                    console.error('选中图层但是未回调任何信息!')
                    return;
                }
                this.echartInfo.id = data.id;
                if(!data.offset){
                    this.offsetInfo = '0,0'
                }else{
                    this.offsetInfo = data.offset.join(',')
                }
                if(data.type=='barcode'){
                    //条形码走这个逻辑
                    this.barcodeSettings = JSON.parse(data.jsonString)
                    vm.rightTabName='name5';
                } else if(data.type=='qrcode'){
                    this.qrcodeSettings = JSON.parse(data.jsonString)
                    vm.rightTabName='name6';
                } else{
                    //图表点击走这个逻辑
                    console.log(this.selectedChartType)
                    this.selectedChartType = data['chartType']
                    this.selectedChartId = data['chartId']
                    this.chartOptions = ''
                    if(!data.options){
                        return;
                    }
                    vm.rightTabName='name2';
                    this.chartsflag=true;
                    if(this.selectedChartType === 'apiUrlType'){
                        this.dataSettings.dataType = 'api'
                        this.dataSettings.apiStatus = '2'
                        this.dataSettings.apiUrl = data.extData['apiUrl']
                        return;
                    }
                    handleChartOptions.call(this, data.options)
                    handleChartExtData.call(this, data.extData)
                }

               /* if (data.extData != "" && typeof(data.extData) != "undefined"){
                    let extData = data.extData;
                    that.dataAllocation.dataType = extData.dataType;
                    that.dataAllocation.status = extData.status;
                    that.dataAllocation.selectId = extData.selectId;
                    that.selectSqlListOpt(that.dataAllocation.selectId)
                    that.dataAllocation.sqlxAxis = extData.sqlxAxis;
                    that.dataAllocation.sqlseries = extData.sqlseries;
                    that.dataAllocation.sqlgroup = extData.sqlgroup;
                    that.dataAllocation.seriesTypeData = extData.seriesTypeData;
                }else {
                    that.dataAllocation.dataType = 'apiechData';
                    that.dataAllocation.status = 'staticData';
                }
                that.dataAllocation.selectOptionData = data.options;
                that.specialChartType = data.options.chartType?data.options.chartType:null;
                that.chartsType = that.dataAllocation.selectOptionData.series[0].type;
                //获取图表类型集
                that.chartsTypeArr = that.uniqueArr(that.dataAllocation.selectOptionData.series.map(item=>{return item.type}));
                //选中判断是否多组图表数据
                that.isMultiChart = that.dataAllocation.selectOptionData.series.length>1?true:false;
                //选中图表绑定配置
                jsontoconfig(that.echartInfo,that.dataAllocation,data.options);*/
            },
            uniqueArr(arr){
                if (Array.hasOwnProperty('from')) {
                    return Array.from(new Set(arr));
                } else {
                    var n = {}, r = [];
                    for (var i = 0; i < arr.length; i++) {
                        if (!n[arr[i]]) {
                            n[arr[i]] = true;
                            r.push(arr[i]);
                        }
                    }
                    return r;
                }
            },
            styleChanges(key){
                if(!key){
                    this.echDataRun();
                }else{
                }
            },
            /**
             * 背景图重复设置改变事件
             * @param value
             */
            backgroundChange(){
                console.log('this.backgroundSettings', this.backgroundSettings)
                xs.setBackground(this.backgroundSettings)
            },
            /**
             * 消除右侧tabpane的显示属性
             */
            clearRightTabpane(){
                this.barcodeSettings = false
                this.qrcodeSettings = false
                this.backgroundSettingShow = false
                this.chartsflag = false
            },
            //条形码/二维码设置改变事件
            onBarcodeChange(settings){
                let id = this.echartInfo.id;
                xs.updateChart(id, settings);
            },
            onClickCell(){
                vm.rightTabName='name1';
                this.clearRightTabpane()
               /* Object.keys(this.backgroundSettings).map(k=>{
                    this.backgroundSettings[k] = ''
                })*/
            },
            //鼠标右键背景图设置 事件
            handleBackground(param){
                this.clearRightTabpane()
                Object.keys(this.backgroundSettings).map(k=>{
                    this.backgroundSettings[k] = param[k]?param[k]:''
                })
                vm.rightTabName='name4';
                this.backgroundSettingShow = true
            },
            removeBackground(){
                Object.keys(this.backgroundSettings).map(k=>{
                    this.backgroundSettings[k] = ''
                })
                xs.setBackground({path:false})
            },
            //文件上传成功 修改excel背景图
            backgroundImgUploadSuccess(res){
                if(!res.success){
                    this.$Message.warning(res.message);
                }else{
                    let path = res.message
                    if(path.indexOf('http')<0){
                        if(path.indexOf('/')!=0){
                            path = '/' + path
                        }
                    }
                    this.backgroundSettings['path'] = path
                    if(!this.backgroundSettings['repeat']){
                        this.backgroundSettings['repeat'] = 'repeat'
                    }
                    xs.setBackground(this.backgroundSettings)
                }
            },
            //图表背景图上传成功
            chartBackgroundUploadSuccess(res,chartBackground,callback){
                if(!res.success){
                    this.$Message.warning(res.message);
                }else{
                    let path = res.message
                    if(path.indexOf('http')<0){
                        if(path.indexOf('/')!=0){
                            path = '/' + path
                        }
                    }
                    this.chartBackground['image'] = path
                    callback&&callback(path);
                    this.chartBackgroundChange(chartBackground);
                }
            },
            //设置图表背景色
            chartBackgroundChange(chartBackground){
                if(chartBackground['enabled']){
                    //启用设置
                    let imageSrc = chartBackground['image']
                    let color = chartBackground['color']
                    if(imageSrc){
                        this.chartOptions['backgroundColor'] = {
                            src: imageSrc
                        }
                    }else if(color){
                        this.chartOptions['backgroundColor'] = color
                    }else{
                        if(this.chartOptions['backgroundColor']){
                            delete this.chartOptions['backgroundColor']
                        }
                    }
                }else{
                    if(this.chartOptions['backgroundColor']){
                        delete this.chartOptions['backgroundColor']
                    }
                }
                //TODO 图标背景图有个坑 项目地址切换导致图片显示不了
                let id = this.echartInfo.id;
                console.info("chartOptions",chartBackground)
                xs.updateChart(id, this.chartOptions);
            },
            //获取图片预览图
            getBackgroundImg(){
                let path = this.backgroundSettings['path']
                if(path){
                    if(path.indexOf('http')<0){
                        path = window.location.origin+baseFull+path
                    }
                }
                return path
            },
            //获取图片预览图
            getChartBackgroundImg(){
                let path = this.chartBackground['image']
                if(path){
                    if(path.indexOf('http')<0){
                        path = window.location.origin+baseFull+path
                    }
                }
                return path
            },
            //删除背景图意味着要重置背景
            removeChartBackground(chartBackground){
                this.chartBackgroundChange(chartBackground);
            },
            //图片上传文件大小
            handleMaxSize (file,size) {
                console.log("file===>",file)
                console.log("size===>",size)
                this.$Notice.warning({
                    title: '超出文件大小限制',
                    desc: '文件  ' + file.name + ' 太大，请上传'+size+'M以内图片',
                    duration: 6
                });
            },
            //象形图图标上传回调
            pictorialIconUploadSuccess(res,callback){
                if(!res.success){
                    this.$Message.warning(res.message);
                }else{
                    let symbol = 'image://';
                    if(res.message.indexOf('https')==0){
                        symbol += res.message
                    }else{
                        symbol += (window.location.origin+baseFull+'/'+res.message)
                    }
                    this.pictorialSettings['symbol'] = symbol
                    callback&&callback(symbol);
                    for(let item of this.chartOptions['series']){
                        item['symbol'] = symbol
                    }
                    let id = this.echartInfo.id;
                    xs.updateChart(id , this.chartOptions);
                }
            },
            getPathBySymbol(){
                let symbol = this.pictorialSettings['symbol']
                if(!symbol){
                    let path =  window.location.origin+baseFull+'/jmreport/desreport_/chartsImg/pictorialIcon/spirits.png'
                    return path;
                }else{
                    return symbol.replace('image://','')
                }
            },
            onAxisYConfigChange(value){
                for(let item of this.sqlDataFieldList){
                    if(item['title']==value){
                        this.dataSettings['yText'] = item['fieldText']
                        break;
                    }
                }
            },
            onAxisXConfigChange(value){
                for(let item of this.sqlDataFieldList){
                    if(item['title']==value){
                        this.dataSettings['xText'] = item['fieldText']
                        break;
                    }
                }
            },
            timerChange(){
                //时间的变换，更新dataSetting
                let id = this.echartInfo.id;
                xs.updateChartExtData(id,  this.dataSettings);
            },
            onLegendChange(){
                this.onSettingsChange('legend', this.legendSettings)
            },
            onTooltipChange(){
                this.onSettingsChange('tooltip', this.tooltipSettings)
            },
            onGridChange(){
                this.onSettingsChange('grid', this.gridSettings)
            },
            onDoubleYAxisChange(){
                this.onSettingsChange('yAxis', this.doubleyAxisSettings)
            },
            onGeoChange(){
                this.onSettingsChange('geo',this.mapGeoSettings)
            },
            onGraphChange(){
                this.onSeriesChange(this.graphSettings)
            },
            onPictorialChange(pictorialSettings){
                let settings = pictorialSettings
                let arr = this.chartOptions['series']
                let oneSeries = arr[0]
                let option = util.setting2Option(pictorialSettings)
                Object.assign(oneSeries, option)
                /*if(!oneSeries['symbol']){
                    oneSeries['symbol'] = base64_spirits
                }*/
                this.chartOptions['xAxis']['max'] = option['symbolBoundingData']
                let newSeries = []
                newSeries.push(oneSeries)
                if(settings['double']==true){
                    let itemStyle = {
                        normal: {
                            opacity:settings['secondOpacity']
                        }
                    }
                    let twoSeries = Object.assign({},oneSeries,{
                        symbolClip: false,
                        itemStyle: itemStyle
                    })
                    newSeries.push(twoSeries)
                }
                this.chartOptions['series'] = newSeries
                let id = this.echartInfo.id;
                xs.updateChart(id , this.chartOptions);
            },
            onSeriesLabelChange(){
                this.onSeriesChange(this.seriesLabelSettings, 'label')
            },
            //series更新事件
            onSeriesChange(whatSetting, propName){
                let option = {}
                if(!propName){
                    option = util.setting2Option(whatSetting)
                }else{
                    option[propName] = util.setting2Option(whatSetting)
                }
                let arr = this.chartOptions['series']
                for(let i=0;i<arr.length;i++){
                    let item = arr[i]
                    let temp = JSON.parse(JSON.stringify(option))
                    //颜色 走自定义的设定
                    if(this.isMultiChart===true && temp['itemStyle']){
                        temp['itemStyle']['color'] = item['itemStyle']['color']
                    }
                    if(this.isMultiChart===false && item['type']==='line' && temp['isArea'] === false){
                        //设置面积折线
                        temp['areaStyle']=null;
                    }
                    Object.assign(item, temp)
                }
                if(arr[0].type==='gauge'){
                    let c1=this.colorMatchData[0]?this.colorMatchData[0].color:"#91c7ae";
                    let c2=this.colorMatchData[1]?this.colorMatchData[1].color:"#63869E";
                    let c3=this.colorMatchData[2]?this.colorMatchData[2].color:"#C23531";
                    let arr= [[0.2, c1], [0.8, c2], [1, c3]];
                    this.chartOptions['series'][0]['axisLine']['lineStyle'].color =arr
                }
                let id = this.echartInfo.id;
                console.info(this.chartOptions)
                xs.updateChart(id , this.chartOptions);
            },
            onSettingsChange(optionKey, whatSetting){
                let option = null;
                if(typeJudge(whatSetting,'Array')){
                     option = util.setting2Array(whatSetting)
                }else{
                     option = util.setting2Option(whatSetting)
                }
                this.chartOptions[optionKey] = option
                let id = this.echartInfo.id;
                //地图注册
                if(this.chartOptions['geo']){
                    let map = this.$refs.mapModal.allMapList.filter(item=>item.name===this.chartOptions['geo']['map']);
                    if(map[0]) {
                        xs.registerMap(this.chartOptions['geo']['map'], map[0].data)
                    }
                }
                xs.updateChart(id , this.chartOptions);
            },
            //选择图表sql数据集
            onSelectSqlData(){
                let value = this.dataSettings['dataId']
                Object.keys(this.dataSettings).map(k=>{
                    this.dataSettings[k]=''
                })
                this.dataSettings['dataType'] = 'sql'
                this.dataSettings['dataId'] = value
                this.sqlDataList.forEach(item=>{
                    if (item.dbId === value){
                        // title  filedText
                        this.sqlDataFieldList = item.children;
                        this.dataSettings['dbCode'] = item.code
                    }
                });
            },
            onSelectSqlData2(){
                let value = this.dataSettings['dataId1']
                this.dataSettings['dataId1'] = value
                this.sqlDataList.forEach(item=>{
                    if (item.dbId === value){
                        this.sqlDataFieldList2 = item.children;
                    }
                });
            },
            onSelectApiData(value){
                let arr = this.apiDataList.filter(item=>{
                    return item.dbId === value
                })
                if(arr.length>0){
                    this.dataSettings['dbCode'] = arr[0].code
                    this.dataSettings['apiUrl'] = arr[0].apiUrl
                }
            },
            runChart(){
                let id = this.echartInfo.id;
                this.dataSettings['chartType'] = this.selectedChartType
                this.dataSettings['chartId'] = this.selectedChartId
                this.dataSettings['run'] = 1
                this.runByDataSettings(id, this.chartOptions, this.dataSettings)
            },
            /**
             * 刷新图表
             * @param chartId 图表id
             * @param options 图表options
             * @param settings 图表extData
             */
            runByDataSettings(chartId, options, settings){
                let dataId = settings.dataId
                const that = this
                if (settings.dataType === 'sql') {
                    $jm.qurestSql(dataId, (result)=> {
                        if(settings['chartType']==='graph.simple'){
                            $jm.qurestSql(settings.dataId1, (res)=> {
                                let { source,target }= settings;
                                let links = res.map((item,index)=>{
                                    return {'source':item[source],'target':item[target]}
                                })
                                refreshChart(chartId, options, settings,{data:result,links:links})
                            })
                        }else{
                            refreshChart(chartId, options, settings,result)
                        }
                    })
                }else if(settings.dataType === 'api'){
                    Object.assign(settings,{axisX: 'name', axisY: 'value', series: 'type'})
                    if(settings.apiStatus == '0'){
                        let str = that.apiStaticDataList;
                        refreshChart(chartId, options, settings, JSON.parse(str))
                    }else if(settings.apiStatus == '1'){
                        $jm.qurestApi(dataId,function (res) {
                            let result = settings['chartType']==='graph.simple'?res : res.data;
                            refreshChart(chartId, options, settings, result)
                        })
                    }else if(settings.apiStatus == '2'){
                        $jm.qureyByApiUrl(settings.apiUrl, (res)=> {
                            if(res.option){
                                console.log("接口返回信息",res.option);
                                //判断请求到的json的数据类型
                                getSelectType.call(that, res.option);
                                let chartExtData=Object.assign(settings,{dataId:chartId,chartType:that.selectedChartType,chartId:this.selectedChartId})
                                xs.updateChart(chartId , res.option);
                                //更新ExtData
                                xs.updateChartExtData(chartId,chartExtData);
                                if(that.selectedChartType !== 'apiUrlType'){
                                    //绑定样式
                                    handleChartOptions.call(that, res.option)
                                }
                            }
                        })
                    }
                }
            },
            //将配置运行为图表
            echDataRun() {
                let that = this;
                let id = that.echartInfo.id;
                if(!that.dataAllocation.selectOptionData.series) return;
                let charType = that.dataAllocation.selectOptionData.series[0].type;
                //动态api数据获取
                if (that.dataAllocation.dataType === 'sqlechData' && that.dataAllocation.selectId != '') {
                    let sqlxAxis = that.dataAllocation.sqlxAxis;
                    let sqlseries = that.dataAllocation.sqlseries;
                    let sqlgroup = that.dataAllocation.sqlgroup;
                    let sqltype = that.dataAllocation.sqltype;
                    let seriesTypeData = that.dataAllocation.seriesTypeData;
                    $jm.qurestSql(that.dataAllocation, (result)=> {
                        let backObj = querySqlData(charType, result, sqlxAxis, sqlseries,sqlgroup,seriesTypeData,that.dataAllocation.optionData);
                        that.dataAllocation.optionData = JSON.stringify(backObj);
                    })
                } else if (that.dataAllocation.dataType === 'apiechData' && that.dataAllocation.selectId != '') {
                    if (that.dataAllocation.status == 'dynamicData') {
                        $jm.qurestApi(that.dataAllocation,function (res) {
                            let backObj = queryApiData(charType, res, that.dataAllocation.optionData);
                            that.dataAllocation.optionData = JSON.stringify(backObj);
                        })
                    }
                }
                //静态数据组装json
                let options = configtojson(that.echartInfo, that.dataAllocation.optionData, this.dataAllocation.selectOptionData);
                //通过id修改配置
                let chartExtData = {};
                chartExtData.dataType = that.dataAllocation.dataType;
                chartExtData.status = that.dataAllocation.status;
                chartExtData.selectId = that.dataAllocation.selectId;
                chartExtData.sqlxAxis = that.dataAllocation.sqlxAxis;
                chartExtData.sqlseries = that.dataAllocation.sqlseries;
                chartExtData.sqlgroup = that.dataAllocation.sqlgroup;
                chartExtData.sqltype = that.dataAllocation.sqltype;
                chartExtData.seriesTypeData = that.dataAllocation.seriesTypeData;
                xs.updateChart(id , options);
                xs.updateChartExtData(id,chartExtData);
                let data = {};
                data.id = id;
                data.options = options;
                data.extData = chartExtData;
                this.selectChart(data);
            },
            //打开图表选择弹框
            addChartModule(){
                this.selectedChartType="";
                this.selectedChartId="";
                this.chartModule = true;
            },
            //添加图表确定事件
            okAddChart(){
                //sureAddchart
                if(!this.selectedChartType){
                    this.$Message.warning('未曾选中图表！');
                    this.chartModule = false;
                }else{
                   let selectedChartId=this.selectedChartId;
                    $http.get({
                        url:apis.addChart,
                        data:{
                            "chartType":this.selectedChartId
                        },
                        success:(result)=>{
                            addChartPreHandler.call(this, result)
                            result.backgroundColor = '#fff'
                            //.image = this.abc
                            xs.addChart(result,null,(data)=>{
                                this.selectChart(data)
                                xs.updateChartExtData(data.id, {chartId:selectedChartId});
                            });

                            this.chartModule = false;
                            this.specialChartType="";
                            //判断图表类型
                            this.isMultiChart=result.series.length>1?true:false;
                            this.chartsflag = true;
                        }
                    })
                }
            },
            dataTypeChange(){
                this.seriesTypeData=[];
            },
            //页面有图表时显示样式和数据tab
            tabPaneShow(){
                this.chartsflag = true;
            },
            //进入设计页面或刷新时运行所有图表
            refreshAllChart(chartList){
                let that = this;
                for(let item of chartList){
                    let id = item.layer_id;
                    let options = JSON.parse(item.config);
                    that.runByDataSettings(id, options, item.extData)
                }
            }
        }
    }
)

//选中绑定配置
function jsontoconfig(echartInfo,dataAllocation,option){
    //样式
    jsontoconfigStyle(echartInfo,option,option.series[0].type);
    //根据图表类型处理数据
    if (option.series[0].type === 'bar' || option.series[0].type === 'line'  || option.series[0].type === 'scatter'){
        let optdata = '{"categories":'+JSON.stringify(option.xAxis.data?option.xAxis.data:'',null, "\t") + "," + '"series":'+ JSON.stringify(option.series,null, "\t")+"}";
        dataAllocation.optionData = optdata;
    }else if (option.series[0].type === 'pie'||option.series[0].type === 'radar'){
        dataAllocation.optionData = JSON.stringify(option.series[0].data, null, "\t");

        if(option.series[0].data.length>0){
            //自定义颜色配置
            let data=option.series[0].data.map(item=>{
                let color={"color1":null};
                if(item.itemStyle){
                    color={"color1":item.itemStyle.color}
                }
                return color
            })
            echartInfo.colorMatchData=data.filter(item=>item.color1 && item.color1.length>0)
        }
    }else if(option.series[0].type === 'map'){
        dataAllocation.optionData = null;
    }
}

//配置刷新运行json
function configtojson(echartInfo,optionData,selectOptionData){

    //根据类型处理数据
    let obj =optionData && optionData.length>0?JSON.parse( optionData ):null;
    if (selectOptionData.series[0].type === 'bar'  || selectOptionData.series[0].type === 'line' || selectOptionData.series[0].type === 'scatter'){
        selectOptionData.xAxis.data = obj.categories;
        selectOptionData.series=obj.series;
    }else if (selectOptionData.series[0].type === 'pie'){
        var copyObj = JSON.parse(JSON.stringify(obj));
        //是否排序
        copyObj =echartInfo.autoSort?copyObj.sort(function (a, b) {return a.value - b.value;}):obj;
        copyObj.forEach(function(item, index){
            //设置颜色
            var color=getMatchColor(echartInfo,index);
            if(item.itemStyle){
                item.itemStyle.color = color;
            }else{
                item.itemStyle={'color':color}
                //Vue.set(item, 'itemStyle', {'color':color})
            }
        });
        selectOptionData.series[0].data = copyObj;
        // 图例数据设置
        selectOptionData.legend.data=selectOptionData.series[0].data.map(item=>{return item.name})
    }else if(selectOptionData.series[0].type === 'map'){
        if(obj && obj.length>0){
            selectOptionData.geo.map=obj[0].name
        }else{
            selectOptionData.geo.map='china'
        }
    }

    //样式
    configtojsonStyle(selectOptionData,echartInfo,selectOptionData.series[0].type);

    return selectOptionData;
}

//转换请求api数据
function queryApiData(charType,result,optionData) {
    let obj =optionData && optionData.length>0?JSON.parse( optionData ):null;
    if (charType === 'bar' || charType === 'line'){
        let xAxisData = [];
        let seriesData = [];
        result.data.forEach(item=>{
            for(var d in item) {
                if (d === 'name'){
                    xAxisData.push(item[d]);
                }
                if (d === 'value'){
                    seriesData.push(item[d]);
                }
            }
        });
        obj.categories = xAxisData;
        //包含分类多组数据处理
        if(result.category){
            let series=[];
            //设置图例数据
            vm.echartInfo.legendData=result.category;
            result.category.forEach((name,index)=>{
                //获取series默认样式
                let commonObj=Object.assign(obj.series[0],{name:name,data:[]});
                //判断原有样式是否存在
                let hasSeries = obj.series.filter(item=>item.name === name);
                if(hasSeries!=null && hasSeries.length>0) {
                    commonObj=Object.assign(hasSeries[0],{name:name,data:[]});
                }
                //多种图表的series公共样式获取
               /* if(result.type && result.type.length>0){
                    let filter = obj.series.filter(serie=>serie.type == result.type[index]);
                    if(filter&&filter.length>0){
                        commonObj=Object.assign(filter[0],{name:name,data:[]});
                    }
                }*/
                let seriesObj=JSON.parse(JSON.stringify(commonObj));
                //获取series的data数据集
                seriesObj.data=seriesData.map(item=>{
                    return item[index]
                });
                series[index]=seriesObj;
            });
            obj.series=series;
        }else{
            obj.series[0].data = seriesData;
        }
        return obj;
    }else if (charType === 'pie'){
        obj = result.data;
        return obj;
    }else if(charType === 'scatter'){
        obj.series[0].data = result.data.map(item=>{return [item.name,item.value]});
        return obj;
    }else if(charType === 'map'){
        obj = result.data;
        xs.registerMap(obj[0].name, obj[0].map);
        return obj;
    }
}

//转换请求sql数据
function querySqlData(charType,result,sqlxAxis,sqlseries,sqlgroup,seriesTypeData,optionData) {
    let obj = JSON.parse(optionData);
    let resultArr=JSON.parse(JSON.stringify(result));//记录结果集数据
    if (charType === 'bar' || charType === 'line'){
        let xAxisData = [];
        let seriesData = [];
        let legendData = [];//图例数据
        //饼图、柱形图特有样式
        let linestyle={"yAxisIndex": 1,"step": false,"showSymbol": true,"smooth": false,"symbolSize":5,"lineStyle":{"width":2}};
        let barstyle={"barWidth": 15,"barMinHeight": 2};
        result.forEach(item=>{
            for(var d in item) {
                if (xAxisData.indexOf(item[d]) != -1){
                    let index = xAxisData.indexOf(item[d]);
                    seriesData[index] = (parseFloat(seriesData[index])  +  parseFloat(item[sqlseries]));
                    delete item[sqlseries];
                }else {
                    if (d === sqlxAxis){
                        xAxisData.push(item[d]);
                    }
                    if (d === sqlseries){
                        seriesData.push(item[d]);
                    }
                    if (d === sqlgroup && legendData.indexOf(item[d]) == -1){
                        legendData.push(item[d]);
                    }
                }
            }
        });
        obj.categories = xAxisData;
        //图例数据大于0，多组分类数据
        if(legendData.length>1){
            //设置图例数据
            vm.echartInfo.legendData=legendData;
            //处理分组数据
            var series=[];
            legendData.forEach((name,index)=>{
                //获取series公共样式
                var commonObj=Object.assign(obj.series[0],{ name:name,data:[]});
                if(legendData.length > 1){
                    //多组多案例
                    let hasSeries = obj.series.filter(item=>item.name === name);
                    if(hasSeries!=null && hasSeries.length>0){
                        //设置存在好的样式
                        commonObj=Object.assign(hasSeries[0],{ name:name,data:[]});
                    }
                    //自定义的图表类型（折柱）
                    if(vm.specialChartType === 'linebar'){
                        //判断系列自定义样式
                        var filterArr = seriesTypeData.filter(seriesType=>seriesType.name === name);//获取到对应系列的类型
                        if(filterArr && filterArr.length>0){
                            //判断系列自定义样式
                            let type = filterArr[0].type;
                            if(type === 'line'){
                                commonObj=Object.assign({ name:name,data:[],type:type,itemStyle:obj.series[0].itemStyle },linestyle)
                            }else{
                                //柱子样式
                                let itemStyle =JSON.parse(JSON.stringify(obj.series[0].itemStyle));
                                if(Object.prototype.toString.call(itemStyle.normal.barBorderRadius).slice(8, -1) === 'Undefined'){
                                    itemStyle.normal.barBorderRadius=0;
                                }
                                commonObj=Object.assign({ name:name,data:[],type:type,itemStyle:itemStyle},barstyle)
                            }
                        }
                    }
               }

                let seriesObj=JSON.parse(JSON.stringify(commonObj));
                //获取series的data数据集
                for(let item of resultArr){
                    if(seriesObj.data.length==xAxisData.length){
                        break;
                    }
                    if(item[sqlgroup] === name){
                        seriesObj.data.push(item[sqlseries]);
                    }
                }
                series[index]=seriesObj;
            });
            console.log("series===>",series);
            obj.series=series;
        }else{
            obj.series[0].data = seriesData;
        }
        return obj;
    }else if (charType === 'pie'){
        let objpie = [];
        result.forEach(item=>{
            var objres = {};
            for(var d in item) {
                if (d === sqlxAxis){
                    objres['name'] = item[d];
                }
                if (d === sqlseries){
                    objres['value'] = item[d];
                }
            }
            objpie.push(objres);
        });
        obj = mergeObject(objpie);
        return obj;
    }else if(charType === 'scatter'){
        obj.series[0].data = result.map(item=>{
            return [item[sqlxAxis],item[sqlseries]]
        });
        return obj;
    }
}

//数组对象去重
function mergeObject(array) {
    var arrayFilted = [];
    array.forEach(value=>{
        if ( arrayFilted.length == 0 ) {
            arrayFilted.push(value);
        }else{
            let flag = true;
            arrayFilted.forEach(valueIndex=>{
                if (valueIndex.name && valueIndex.name === value.name) {
                    valueIndex.value = valueIndex.value + value.value;
                    flag = false;
                }
            });
            if (flag){
                arrayFilted.push(value);
            }
        }
    });
    return arrayFilted;
}

function xsSetNewdata(data,newxsdata){
    xs.data.rows["_"][data.sri].cells[data.sci].text=newxsdata;
    vm.excel.excelValue = newxsdata;
    xs.sheet.reload()
}

function setExcelData({ri=0,ci=0},text,isDrag=false,style,merge) {
    let cell = {[ci]:{"text":text}};
    //第一个单元格样式
    if(style || style==0){
        cell[ci]['style']=style;
    }
    if(merge){
        //加入合并单元格
        cell[ci]["merge"] = merge;
    }
    let tableData = {};
    let rowsData = xs.getData().rows;
    if(rowsData[ri]){
        //有数据
        let cells = {...rowsData[ri].cells,...cell};
        tableData = {"rows":{
                ...rowsData,[ri]:{cells,isDrag}
            }
        };
    }else{
        tableData={
            "rows":{
                ...rowsData,[ri]:{"cells":cell,isDrag}
            }
        }
    }

    //加入高度设置
    const row = xs.getData().rows[ri];
    const height = row && row.height;
    if(height){
        tableData.rows[ri].height = height;
    }

    xs.loadData(Object.assign({},tableData,{background: xs.data.background}));

}

function setExcelCoordinate(ev){
    let data = xs.data.getCellRectByXY(ev.offsetX, ev.offsetY);
    if(data.ri<0 || data.ci<0) return;
    vm.excel.coordinate = `${data.ri},${data.ci}`;
    vm.excel.ri = data.ri;
    vm.excel.ci = data.ci;
}

function getMatchColor(echartInfo,index){
    var matchColors = echartInfo.colorMatchData || [];
    if (matchColors && matchColors[index]) {
        var color1 = matchColors[index].color1;
        return color1;
    }else{
        return null;
    }
}

let sheetDiv = document.getElementById('x-spreadsheet-demo');

//单击事件
sheetDiv.onclick=function(ev){
    Object.keys(vm.excel).map(k=>{
        vm.excel[k]=''
    })
    setExcelCoordinate(ev);
    let row = xs.getData().rows[vm.excel.ri];
    let text="";
    if(row){
        text = row.cells[vm.excel.ci]?row.cells[vm.excel.ci].text:"";
    }
    vm.excel.excelValue = text;
    let cellMe = [];
    if (text && text != "" && text.indexOf("#{") != -1){
        cellMe = subStringStr(text,"#{","}").split(".");
        let field = cellMe[1];
        if (field.indexOf("group(") != -1){
            vm.excel.polyWay = "group"
        }else {
            vm.excel.polyWay = "select"
        }
        if (field.indexOf("groupRight(") != -1){
            vm.excel.direction = "right"
            vm.excel.polyWay = "group"
            vm.$refs.excelPolyWay.disabled = true;
        }else{
            vm.excel.direction = "down"
            vm.$refs.excelPolyWay.disabled = false;
        }
        vm.excel.isDict = row.cells[vm.excel.ci]?row.cells[vm.excel.ci].isDict:"";
        vm.excel.dictCode = row.cells[vm.excel.ci]?row.cells[vm.excel.ci].dictCode:"";
    }

}

function subStringStr(str,strStart,strEnd){
    /* 找出指定的2个字符在 该字符串里面的 位置 */
    let strStartIndex = str.indexOf(strStart);
    let strEndIndex = str.indexOf(strEnd);
    /* index 为负数 即表示该字符串中 没有该字符 */
    if (strStartIndex < 0) {
        return "";
    }
    if (strEndIndex < 0) {
        return "";
    }
    /* 开始截取 */
    let result = str.substring(strStartIndex, strEndIndex).substring(strStart.length);
    return result;
}

// 双击事件
sheetDiv.ondblclick=function(ev){
    console.log("双击=====");
}

// 拖拽开始
sheetDiv.ondragover = function (ev) {
    console.log("拖拽开始=====");
    ev.preventDefault(); //阻止向上冒泡
};

// 拖拽结束
sheetDiv.ondrop = function (ev) {
    console.log("拖拽结束=====");
    ev.preventDefault();

    let id = ev.dataTransfer.getData('id');
    let dbText = ev.dataTransfer.getData('dbText').trim();
    dbText = vm.treeObj[dbText];
    //设置pop后的text文本获取
    let text = $("#"+id).find("[class='ivu-tooltip-inner']").text().trim();
    let excelText = `#{${dbText}.${text}}`;
    excelText = excelText.replace(/\((.+?)\)/g,'');
    let data = xs.data.getCellRectByXY(ev.offsetX, ev.offsetY);
    const cell = xs.data.getCell(data.ri,data.ci);
    const style = cell && cell.style;
    const merge = cell && cell.merge;
    setExcelCoordinate(ev);
    setExcelData({ri:data.ri,ci:data.ci},excelText,true,style,merge);
    vm.excel.excelValue = excelText;
    //设置撤销操作
    xs.sheet.toolbar.undoEl.setState(false);
    //{"rows":{"3":{"cells":{"5":{"text":""}}},"len":100}}
    const rowData = JSON.parse(JSON.stringify(xs.data.rows["_"]));
    rowData[data.ri]["cells"][data.ci].text="";

    xs.data.history.undoItems.push(JSON.stringify({"rows":rowData}));

    vm.excel.polyWay = 'select';
    vm.excel.direction = 'down';

};

function addDrag(){
    setTimeout(function(){
        if(!xs) return;
        let {ri,ci} = xs.data.selector;
        vm.excel.ri = ri;
        vm.excel.ci  =ci;
        vm.excel.coordinate = `${ri},${ci}`;
        //绑定拖拽事件
        let index = 0;
        $("#dataTree ul .ivu-tree-children").each(function(){
            let $li = $(this).find("li");
            $li.attr("draggable","true");
            $li.attr("id","label"+(++index));

            let dbText = $($li.parent().siblings('span')[1]).text();
            $li[0].ondragstart = function (ev) {
                //dataTransfer.setData() 方法设置被拖数据的数据类型和值
                ev.dataTransfer.setData("id", this.id);
                ev.dataTransfer.setData("dbText", dbText);
            };
        });
        /*if (xs.data.chartList && xs.data.chartList.length > 0){
            vm.tabPaneShow();
            vm.refreshAllChart(xs.data.chartList);
        }*/
    },300);
}

//图表选中样式绑定
function jsontoconfigStyle(echartInfo,option,charType){
    //标题
    echartInfo.titleText = option.title.text?option.title.text:'';
    echartInfo.titleFontSize = option.title.textStyle.fontSize?option.title.textStyle.fontSize: 20;
    echartInfo.titleFontWeight = option.title.textStyle.fontSize?option.title.textStyle.fontWeight:'bolder';
    echartInfo.titleColor =  option.title.textStyle.color?option.title.textStyle.color:'#c43632';
    echartInfo.titleLocation = option.title.left;
    //饼图
    if(charType === 'pie'){
        echartInfo.isRadius = option.series[0].isRadius;
        echartInfo.radius =  option.series[0].isRadius?option.series[0].pieRadius:'55%';
        if(option.series[0].isRadius){
            echartInfo.pieRadius = option.series[0].pieRadius?option.series[0].pieRadius:'45%,55%';
        }
        echartInfo.roseType = option.series[0].roseType=='radius'?true:false;
        echartInfo.minAngle = option.series[0].minAngle;
        echartInfo.pieLabelPosition = option.series[0].label.position;
        echartInfo.autoSort = option.series[0].autoSort;
        echartInfo.notCount = option.series[0].notCount;
        //数值显示
        echartInfo.numerShow = option.series[0].label.show;
        echartInfo.numerTextSize = option.series[0].label.textStyle.fontSize;
        echartInfo.numerTextcol = option.series[0].label.textStyle.color;
        echartInfo.numerTextweig = option.series[0].label.textStyle.fontWeight;
        //边距
        echartInfo.gridLeft = option.series[0].left;
        echartInfo.gridTop = option.series[0].top;
        echartInfo.gridRight = option.series[0].right;
        echartInfo.gridBottom = option.series[0].bottom;
    }
    if (charType === 'bar'|| charType === 'line' || charType === 'scatter'){
        //series为多数据多图表的处理
        let colorList=[];
        option.series.forEach(item=>{
            if (item.type === 'bar'){
                //柱体
                echartInfo.barWidth = item.barWidth;
                echartInfo.barRadius = item.itemStyle.normal.barBorderRadius;
                echartInfo.barMinHeight = item.barMinHeight;
                echartInfo.seriesItemNorCol = option.series.length==1?item.itemStyle.normal.color:'';
                if(vm.isMultiChart && item.itemStyle.normal.color)
                colorList.push({name:item.name,color1:item.itemStyle.normal.color})
            }
            if (item.type === 'line'){
                echartInfo.step = item.step; //阶梯线图
                echartInfo.showSymbol = item.showSymbol;//标记点
                echartInfo.smooth = item.smooth;//平滑曲线
                echartInfo.symbolSize =item.symbolSize;// 设置折线上圆点大小
                echartInfo.linewidth = item.lineStyle.width;// 设置线宽
                echartInfo.seriesLinemNorCol = option.series.length==1?item.lineStyle.color:null;//线条颜色
                if(vm.isMultiChart && item.lineStyle.color)
                colorList.push({name:item.name,color1:item.lineStyle.color})
            }
            if(item.type === 'scatter'){
                echartInfo.seriesItemNorCol =  item.itemStyle.normal.color?item.itemStyle.normal.color:'';
                echartInfo.symbolSize =item.symbolSize;// 设置折线上圆点大小
            }
        })
        if(vm.isMultiChart)
        echartInfo.colorMatchData=vm.uniqueArr(colorList).map(color=>{if(color.color1) return {color1:color.color1}})
        //X轴
        echartInfo.xaxisShow = option.xAxis.show;
        echartInfo.xaxisText = option.xAxis.name;
        echartInfo.xaxisLine = option.xAxis.splitLine.show;
        echartInfo.xaxisLinecol = option.xAxis.splitLine.lineStyle.color;
        echartInfo.xaxisTextsize = option.xAxis.axisLabel.textStyle.fontSize;
        echartInfo.axisLabelRotate = option.xAxis.axisLabel.rotate;
        //Y轴（多数据处理）
        let yAxis=typeJudge(option.yAxis,'Array')?option.yAxis[0]:option.yAxis;
        let yAxisName=typeJudge(option.yAxis,'Array')?option.yAxis.map(item=>{return item.name}).join(","):option.yAxis.name;
        echartInfo.yaxisShow = yAxis.show;
        echartInfo.yaxisText = yAxisName;
        echartInfo.yaxisLine = yAxis.splitLine.show;
        echartInfo.yaxisLinecol = yAxis.splitLine.lineStyle.color;
        echartInfo.yaxisTextsize = yAxis.axisLabel.textStyle.fontSize;
        //数值显示
        echartInfo.numerShow = option.series[0].itemStyle.normal.label.show;
        echartInfo.numerTextSize = option.series[0].itemStyle.normal.label.textStyle.fontSize;
        echartInfo.numerTextcol = option.series[0].itemStyle.normal.label.textStyle.color;
        echartInfo.numerTextweig = option.series[0].itemStyle.normal.label.textStyle.fontWeight;
        //轴边距
        echartInfo.gridLeft = option.grid.left;
        echartInfo.gridTop = option.grid.top;
        echartInfo.gridRight = option.grid.right;
        echartInfo.gridBottom = option.grid.bottom;
        //轴线和字体颜色
        echartInfo.axisLabelTextCol = option.xAxis.axisLabel.textStyle.color;
        echartInfo.axisLineLineCol = option.xAxis.axisLine.lineStyle.color;
    }
    if(charType === 'map'){
        //区域线的宽度、颜色、区域颜色
        echartInfo.scale=option.geo.zoom;
        echartInfo.xaxisTextsize=option.geo.label.fontSize;
        echartInfo.numerTextcol=option.geo.label.color;
        echartInfo.numerTextHighCol=option.geo.emphasis.label.color;
        echartInfo.borderWidth=option.geo.itemStyle.borderWidth;
        echartInfo.areaCol=option.geo.itemStyle.areaColor;
        echartInfo.areaHighCol=option.geo.emphasis.itemStyle.areaColor;
        echartInfo.borderCol=option.geo.itemStyle.borderColor;
        echartInfo.gridLeft = option.geo.left;
        echartInfo.gridTop = option.geo.top;
        echartInfo.gridRight = option.geo.right;
        echartInfo.gridBottom = option.geo.bottom;
    }else{
        //提示框
        echartInfo.tooltipShow = option.tooltip.show;
        echartInfo.tooltipTextSize = option.tooltip.textStyle.fontSize;
        echartInfo.tooltipTextcol = option.tooltip.textStyle.color;
        //图例
        echartInfo.legendShow = option.legend.show;
        echartInfo.legendData = option.legend.data;
        echartInfo.legendTop = option.legend.top;
        echartInfo.legendLeft = option.legend.left;
        echartInfo.legendOrient = option.legend.orient;
        echartInfo.legendTextcol = option.legend.textStyle.color?option.legend.textStyle.color:'';
        echartInfo.legendTextsize = option.legend.textStyle.fontSize;
    }
}

//图表运行json数据样式
function configtojsonStyle(selectOptionData,echartInfo,charType){
    //标题
    selectOptionData.title.text = echartInfo.titleText;
    selectOptionData.title.textStyle.fontSize = echartInfo.titleFontSize;
    selectOptionData.title.textStyle.fontWeight = echartInfo.titleFontWeight;
    selectOptionData.title.textStyle.color = echartInfo.titleColor;
    selectOptionData.title.left = echartInfo.titleLocation;

    if(charType === 'pie'){
        //饼图
        selectOptionData.series[0].isRadius = echartInfo.isRadius;
        selectOptionData.series[0].radius = echartInfo.isRadius? echartInfo.pieRadius.split(',') : '55%';
        selectOptionData.series[0].pieRadius = echartInfo.isRadius? echartInfo.pieRadius : '45%,55%';
        selectOptionData.series[0].roseType=echartInfo.roseType?'radius':"";
        selectOptionData.series[0].minAngle = echartInfo.minAngle;
        selectOptionData.series[0].label.position = echartInfo.pieLabelPosition;
        selectOptionData.series[0].notCount = echartInfo.notCount;
        selectOptionData.series[0].autoSort = echartInfo.autoSort;
        //数值显示
        selectOptionData.series[0].label.show = echartInfo.numerShow;
        selectOptionData.series[0].label.textStyle.fontSize = echartInfo.numerTextSize;
        selectOptionData.series[0].label.textStyle.color = echartInfo.numerTextcol;
        selectOptionData.series[0].label.textStyle.fontWeight = echartInfo.numerTextweig;
        //轴边距
        selectOptionData.series[0].left = echartInfo.gridLeft;
        selectOptionData.series[0].top = echartInfo.gridTop;
        selectOptionData.series[0].right = echartInfo.gridRight;
        selectOptionData.series[0].bottom = echartInfo.gridBottom;
    }
    if (charType === 'bar'|| charType === 'line' || charType === 'scatter'){
        //多数据多图表处理
        selectOptionData.series.forEach(item=>{
            //柱体样式
            if(item.type=='bar'){
                item.barWidth = echartInfo.barWidth;
                item.barMinHeight = echartInfo.barMinHeight;
                item.itemStyle.normal.barBorderRadius = echartInfo.barRadius;
                item.itemStyle.normal.color = echartInfo.seriesItemNorCol;
                if(vm.isMultiChart){
                    echartInfo.legendData.forEach((name,index)=>{
                        if(name === item.name){
                            if(echartInfo.colorMatchData[index])
                            item.itemStyle.normal.color = echartInfo.colorMatchData[index].color1;
                        }
                    })
                }
            }
            //折线样式
            if(item.type=='line') {
                item.step = echartInfo.step; //阶梯线图
                item.showSymbol = echartInfo.showSymbol;//标记点
                item.symbolSize = echartInfo.symbolSize;// 设置折线上圆点大小
                item.smooth = echartInfo.smooth;//平滑曲线
                item.lineStyle.width = echartInfo.linewidth;// 设置线宽
                item.lineStyle.color = echartInfo.seriesLinemNorCol;
                if(vm.isMultiChart){
                    echartInfo.legendData.forEach((name,index)=>{
                        if(name === item.name){
                            if(echartInfo.colorMatchData[index])
                                item.lineStyle.color = echartInfo.colorMatchData[index].color1;
                        }
                    })
                }
            }
            if(item.type === 'scatter'){
                item.itemStyle.normal.color = echartInfo.seriesItemNorCol;
                item.symbolSize = echartInfo.symbolSize;// 设置折线上圆点大小
            }
        })
        //X轴样式
        selectOptionData.xAxis.show = echartInfo.xaxisShow;
        selectOptionData.xAxis.name = echartInfo.xaxisText;
        selectOptionData.xAxis.splitLine.show = echartInfo.xaxisLine;
        selectOptionData.xAxis.splitLine.lineStyle.color = echartInfo.xaxisLinecol;
        selectOptionData.xAxis.axisLabel.textStyle.fontSize = echartInfo.xaxisTextsize;
        selectOptionData.xAxis.axisLabel.rotate = echartInfo.axisLabelRotate;
        //Y轴样式
        if(typeJudge(selectOptionData.yAxis,'Array')){
            selectOptionData.yAxis[0].splitLine.show = echartInfo.yaxisLine; //多y轴分割线显示设置
            selectOptionData.yAxis.forEach((item,index)=>{
                item.show = echartInfo.yaxisShow;
                item.name = echartInfo.yaxisText.indexOf(",")!=-1?echartInfo.yaxisText.split(",")[index]:echartInfo.yaxisText;
                item.splitLine.lineStyle.color = echartInfo.yaxisLinecol;
                item.axisLabel.textStyle.fontSize = echartInfo.yaxisTextsize;
                //y轴线和字体颜色
                item.axisLabel.textStyle.color = echartInfo.axisLabelTextCol;
                item.axisLine.lineStyle.color = echartInfo.axisLineLineCol;
            })
        }else{
            selectOptionData.yAxis.show = echartInfo.yaxisShow;
            selectOptionData.yAxis.name = echartInfo.yaxisText;
            selectOptionData.yAxis.splitLine.show = echartInfo.yaxisLine;
            selectOptionData.yAxis.splitLine.lineStyle.color = echartInfo.yaxisLinecol;
            selectOptionData.yAxis.axisLabel.textStyle.fontSize = echartInfo.yaxisTextsize;
            //y轴线和字体颜色
            selectOptionData.yAxis.axisLabel.textStyle.color = echartInfo.axisLabelTextCol;
            selectOptionData.yAxis.axisLine.lineStyle.color = echartInfo.axisLineLineCol;
        }
        //数值显示
        selectOptionData.series.forEach(item=>{
            item.itemStyle.normal.label.show = echartInfo.numerShow;
            item.itemStyle.normal.label.textStyle.fontSize = echartInfo.numerTextSize;
            item.itemStyle.normal.label.textStyle.color = echartInfo.numerTextcol;
            item.itemStyle.normal.label.textStyle.fontWeight = echartInfo.numerTextweig;
        });
        //图例文字
        if(selectOptionData.series.length==1){
            selectOptionData.legend.data[0] = echartInfo.yaxisText;
            selectOptionData.series[0].name = echartInfo.yaxisText;
        }else{
            if(echartInfo.legendData.length>0){
                selectOptionData.legend.data= echartInfo.legendData
            }
        }
        //轴边距
        selectOptionData.grid.left = echartInfo.gridLeft;
        selectOptionData.grid.top = echartInfo.gridTop;
        selectOptionData.grid.right = echartInfo.gridRight;
        selectOptionData.grid.bottom = echartInfo.gridBottom;
        //轴线和字体颜色
        selectOptionData.xAxis.axisLabel.textStyle.color = echartInfo.axisLabelTextCol;
        selectOptionData.xAxis.axisLine.lineStyle.color = echartInfo.axisLineLineCol;
    }
    if(charType === 'map'){
        //区域线的宽度、颜色、区域颜色
        selectOptionData.geo.zoom=echartInfo.scale;
        selectOptionData.geo.label.fontSize=echartInfo.xaxisTextsize;
        selectOptionData.geo.label.color=echartInfo.numerTextcol;
        selectOptionData.geo.emphasis.label.color=echartInfo.numerTextHighCol;
        selectOptionData.geo.itemStyle.borderWidth=echartInfo.borderWidth;
        selectOptionData.geo.itemStyle.areaColor=echartInfo.areaCol;
        selectOptionData.geo.emphasis.itemStyle.areaColor=echartInfo.areaHighCol;
        selectOptionData.geo.itemStyle.borderColor=echartInfo.borderCol;
        return false;
    }
    //提示框
    selectOptionData.tooltip.show = echartInfo.tooltipShow;
    selectOptionData.tooltip.textStyle.fontSize = echartInfo.tooltipTextSize;
    selectOptionData.tooltip.textStyle.color = echartInfo.tooltipTextcol;
    //图例
    selectOptionData.legend.show = echartInfo.legendShow;
    //selectOptionData.legend.itemWidth = echartInfo.legendItemWidth;
    selectOptionData.legend.top = echartInfo.legendTop;
    selectOptionData.legend.left = echartInfo.legendLeft;
    selectOptionData.legend.orient = echartInfo.legendOrient;
    if(echartInfo.legendTextcol)
    selectOptionData.legend.textStyle.color = echartInfo.legendTextcol;
    selectOptionData.legend.textStyle.fontSize = echartInfo.legendTextsize;
}

// addDrag();
//判断数据类型
function typeJudge(o,type){
    return Object.prototype.toString.call(o).slice(8, -1) === type
}

function addChartModalSelectedStyle() {
    let chartArray = document.querySelectorAll('.chart-modal-content .ivu-col');
    console.log('chartArray',chartArray)
    for(var a=0;a<chartArray.length;a++){
        var chart = chartArray[a];
        chart.onmouseover = function(e){
            var tar = e.target
            var activeDiv;
            if(tar.tagName === 'IMG'){
                activeDiv = tar.parentNode;
            }else{
                var col = tar.className
                if(col && col.indexOf('ivu-col')>=0) {
                    activeDiv = tar.querySelector('div')
                }
            }
            if(activeDiv&&activeDiv.parentNode.className.indexOf('no-allowed')>=0){
                return;
            }
            if(activeDiv){
                activeDiv.className = activeDiv.className+ ' chart-active'
            }
        }
        chart.onmouseout = function(e){
            var tar = e.target
            var activeDiv;
            if(tar.tagName === 'IMG'){
                activeDiv = tar.parentNode
            }else{
                var col = tar.className
                if(col && col.indexOf('ivu-col')>=0){
                    activeDiv = tar.querySelector('div')
                }
            }
            if(activeDiv&&activeDiv.parentNode.className.indexOf('no-allowed')>=0){
                return;
            }
            if(activeDiv){
                activeDiv.className=activeDiv.className.replace(' chart-active','')
            }
        }
    }
}

/**
 * 处理extdata 右侧运行后才有
 * @param extData
 */
function handleChartExtData(extData){
    //清空所有值
    Object.keys(this.dataSettings).map(k=>{
        this.dataSettings[k] = ''
    })
    this.dataSettings['chartType'] = this.selectedChartType
    this.dataSettings['chartId'] = extData.chartId
    this.dataSettings.id=this.echartInfo.id;
    //如果没有配置 数据集 则设置默认值
    if(!extData || !extData.dataId){
        this.dataSettings.dataType = 'api'
        this.dataSettings.apiStatus = '0'
        return;
    }
    if(extData.dataId && extData['dataType']=='sql'){
        this.sqlDataList.forEach(item=>{
            if (item.dbId === extData.dataId){
                this.sqlDataFieldList = item.children;
            }
            if (item.dbId === extData.dataId1){
                this.sqlDataFieldList2 = item.children;
            }
        });
    }
    this.dataSettings = {...extData}

}

/**
 * 处理图表背景
 * @param options
 */
function handleChartBackground(options) {
    this.chartBackground.color = '#fff'
    this.chartBackground.repeat = 'repeat'
    this.chartBackground.image = ''
    this.chartBackground.enabled = false
    if(options.backgroundColor){
        this.chartBackground.enabled = true
        let src = options.backgroundColor.src
        if(src){
            this.chartBackground.image = src
        }else{
            let color =  options.backgroundColor
            this.chartBackground.color = color
        }
    }
}

/**
 * 选中图表后需要加载图表的配置信息 到 当前vue实例中去
 */
function handleChartOptions(options){
    clearSettings.call(this)
    this.chartOptions = options
    //处理标题
    this.titleSettings = option2Setting(options.title)
    //处理背景
    handleChartBackground.call(this, options)
    //处理图例
    if(options.legend) {
        this.legendSettings = option2Setting(options.legend)
    }
    //处理提示信息
    if(options.tooltip){
        this.tooltipSettings = option2Setting(options.tooltip)
    }
    /*------  注意下面的属性都不会一定出现在option中 ---------*/
    //处理grid
    if(options.grid){
        this.gridSettings = option2Setting(options.grid)
    }
    //处理xAxis x轴坐标信息
    if(options.xAxis){
        this.xAxisSettings = option2Setting(options.xAxis)
    }
    //处理yAxis y轴坐标信息
    if(options.yAxis){
        if(vm.selectedChartType === 'mixed.linebar'){
            this.yAxisSettings = array2Setting(options.yAxis);
        }else{
            this.yAxisSettings = option2Setting(options.yAxis)
        }
    }
    //处理geo地图信息
    if(options.geo){
        //this.$refs.mapModal.callMapDb();
        this.mapGeoSettings = option2Setting(options.geo)
    }
    //处理雷达图信息
    if(options.radar){
        this.radarSettings = array2Setting(options.radar)
    }

    //终极 处理series
    let arr = options.series
    if(!arr){
        this.$Message.warning('后台json配置有问题！');
        return;
    }
    this.isMultiChart = arr.length>1
    handleSingleSeries.call(this, arr[0])
    if(this.isMultiChart){
        handleMultiSeries.call(this, arr)
    }
    //处理自定义颜色和系列
    handleCustomData.call(this, arr);
    //获取api静态数据
    let chartType = this.selectedChartType || this.dataSettings['chartType'] || ''
    if(!chartType){
        vm.$Message.warning('版本升级导致数据不兼容，请删除图表重新添加!');
    }
    this.apiStaticDataList = getChartDataList(options, chartType)

}

/**
 * 处理一些特有的Series中的配置 如柱状图柱体宽度 折线图线条颜色
 * @param object
 */
function handleSingleSeries(object) {
    let type = object.type
    if(type=='bar'){
        handleBarSettings.call(this, object)
    }else if(type=='line'){
        handleLineSettings.call(this, object)
    }else if(type=='pie'){
        handlePieSettings.call(this, object)
    }else if(type=='scatter'){
        handleScatterSettings.call(this, object)
    }else if(type=='funnel'){
        handleFunnelSettings.call(this, object)
    }else if(type=='graph'){
        handleGraphSettings.call(this, object)
    }else if(type=='pictorialBar'){
        handlePictorialSettings.call(this, object)
    }else if(type=='radar'){
        handleRadarSettings.call(this, object)
    }else if(type=='gauge'){
        handleGaugeSettings.call(this, object)
    }
    if(object.label){
        this.seriesLabelSettings = option2Setting(object['label'])
        this.labelPositionArray = getSeriesLabelPostion.call(this)
        this.seriesLabelSettings.labelPositionArray = this.labelPositionArray
    }
}

/**
 * 处理一些特有的Series中的配置 如折柱图等
 * @param object
 */
function handleMultiSeries(arr) {
    arr.forEach(object=>{
        let type = object.type
        if(type=='bar'){
            handleBarSettings.call(this, object)
        }else if(type=='line'){
            handleLineSettings.call(this, object)
        }
    })
}
/**
 * 处理一些自定义的数据 如自定义系列和自定义颜色等
 * @param object
 */
function handleCustomData(arr) {
    let colorArr = [];
    let colorName = []
    arr.forEach(object=>{
        let type = object.type
        if(type=='bar' || type=='line'){
            if(object.itemStyle && object.itemStyle.color){
                colorArr.push({name:object.name,color:object.itemStyle.color})
            }
            colorName.push(object.name)
        }else if(type=='graph'){
            //关系图自定义的数据
            object.categories.forEach(categoryObj=>{
                if(categoryObj.itemStyle && categoryObj.itemStyle.color){
                    colorArr.push({name:categoryObj.name,color:categoryObj.itemStyle.color})
                }
            })
            colorName=object.categories.map(item=>{return item.name}).filter(function(item, index, self){ return  self.indexOf(item) == index; });
        }else if(type=='scatter'){
            if(object.itemStyle && object.itemStyle.color && object.itemStyle.color.colorStops){
                let colorStops = object.itemStyle.color.colorStops;
                colorArr.push({name:object.name,color:colorStops[0].color,edgeColor:colorStops[1].color})
            }
            colorName.push(object.name)
        }else if(type=='radar'){
            object.data.forEach(item=>{
                if(item.areaStyle && item.areaStyle.color&& item.areaStyle.color!=''){
                    colorArr.push({name:item.name,color:item.areaStyle.color,opacity:item.areaStyle.opacity,lineColor:item.lineStyle.color})
                }
                colorName.push(item.name)
            });

        }else if(type=='pie' || type=='funnel'){
            object.data.map(item=>{
                if(item.itemStyle && item.itemStyle.color){
                    colorArr.push({name:item.name,color:item.itemStyle.color})
                }
                colorName.push(item.name)
            })
        }else if(type=='gauge' ){
            let arr = object.axisLine.lineStyle.color;
            for(j = 0,len=arr.length; j < len; j++) {
                if((arr[0] && arr[0][1]=='#91c7ae') && (arr[1] && arr[1][1]=='#63869E')){
                   break;
                }
                colorArr.push({color:arr[j][1],name:""})
            }
        }
    })
    console.info("自定义颜色",JSON.stringify(colorArr))
    vm.colorMatchData = colorArr
    //特殊图表的系列类型配置
    if(vm.selectedChartType === 'mixed.linebar' || vm.selectedChartType === 'bar.stack'){
        vm.seriesTypeData = arr[0].typeData?arr[0].typeData:[]
    }
    //获取颜色对应的数据 颜色设置的下拉框用
    vm.customColorNameList = colorName

}



function handleBarSettings(object){
    let arr = ['barWidth','barMinHeight','itemStyle','backgroundStyle','showBackground']
    this.barSettings = option2Setting(object, arr)
}

function handleLineSettings(object){
    let arr = ['step','showSymbol','smooth','symbolSize','itemStyle','lineStyle','areaStyle','isArea']
    this.lineSettings = option2Setting(object, arr)
}

function handlePieSettings(object){
    let arr = ['isRadius','radius','minAngle','roseType','isRose']
    this.pieSettings = option2Setting(object, arr)
    this.marginSettings=option2Setting(object,['left','right','top','bottom']);
}

function handleScatterSettings(object){
    let arr = ['symbolSize','itemStyle']
    this.scatterSettings = option2Setting(object, arr)
}

function handleGraphSettings(object){
    let arr = ['layout','links','categories','label','lineStyle','center']
    this.graphSettings = option2Setting(object, arr)
    this.centralPointSettings= option2Setting(object,'center')
}

function handleFunnelSettings(object){
    let arr = ['width','sort','gap','orient']
    this.funnelSettings = option2Setting(object, arr)
    this.marginSettings=option2Setting(object,['left','top','bottom']);
}

function handleGaugeSettings(object){
    let arr = ['radius','detail','axisLabel','axisLine','pointer','itemStyle','axisTick','splitLine','title']
    this.gaugeSettings = option2Setting(object, arr)
}
function handleRadarSettings(object){
    let arr = []
    this.raderSettings = option2Setting(object, arr)
}

function handlePictorialSettings(object) {
    this.legendSettings = false
    this.tooltipSettings = false
    let arr = ['symbolMargin','symbolSize','double','secondOpacity','symbolBoundingData','symbol']
    this.pictorialSettings = option2Setting(object, arr)
}

/**
 * 清除原属性
 */
function clearSettings() {
    /*Object.keys(this.titleSettings).map(k=>{
        this.titleSettings[k]=''
    })*/
    this.chartOptions = ''
    this.titleSettings = {}
    this.legendSettings = false
    this.tooltipSettings = false
    this.mapGeoSettings = false
    this.gridSettings = false
    this.xAxisSettings = false
    this.yAxisSettings = false
    this.doubleyAxisSettings = false
    this.barSettings = false
    this.lineSettings = false
    this.pieSettings = false
    this.scatterSettings = false
    this.funnelSettings = false
    this.marginSettings = false
    this.centralPointSettings = false
    this.pictorialSettings = false
    this.radarSettings = false
    this.gaugeSettings = false
    this.graphSettings = false
    this.seriesLabelSettings = false
    this.labelPositionArray = []
    this.apiStaticDataList = ''
    this.colorMatchData=[]
    this.seriesTypeData=[]
    this.customColorNameList = []
    this.isMultiChart = false
}

/**
 * 图表option转实际配置 单个
 */
function option2Setting(option, keyArray) {
    let setting = {}
    if(option){
        let optionKeys = Object.keys(option)
        for(let i=0;i<optionKeys.length;i++){
            let key = optionKeys[i]
            if(keyArray && keyArray.indexOf(key)<0){
                continue;
            }
            let obj = option[key]
            handleMultiObject(setting, obj, key)
        }
    }
    return setting;
}
/**
 * 图表arr转实际配置 多个
 */
function array2Setting(array, keyArray) {
    let setting = []
    for(let obj of array){
        let option = option2Setting(obj,keyArray);
        setting.push(option);
    }
    return setting;
}

/**
 * 处理多层子对象的属性
 * @param setting
 * @param obj
 * @param key
 */
function handleMultiObject(setting, obj, key){
    if(!obj && obj!=false){
        setting[key] = ''
    }else if(typeof obj=='object'){
        if(obj.constructor==Object){
            Object.keys(obj).map(subKey=>{
                handleMultiObject(setting, obj[subKey], key+'_'+subKey)
            })
        }else if(obj.constructor==Array){
            setting[key] = [...obj]
        }
    }else{
        setting[key] = obj
    }

}


//处理多段下划线属性
function handleMultiUnderline(value, obj, arr, index){
    let key = arr[index]
    if(index==arr.length-1){
        obj[key] = value;
        return;
    }
    if(!obj[key]){
        obj[key] = {}
    }
    handleMultiUnderline(value, obj[key], arr, ++index)
}

function getSeriesLabelPostion() {
    const postion = [
        { value: 'start',text:'线的起始点',type:'line'},
        { value: 'middle',text:'线的中点',type:'line'},
        { value: 'end',text:'线的结束点',type:'line'},

        { value: 'top',text:'上方',type:'bar'},
        { value: 'left',text:'左边',type:'bar'},
        { value: 'right',text:'右边',type:'bar'},
        { value: 'bottom',text:'下方',type:'bar'},
        { value: 'inside',text:'内部',type:'bar'},

        { value: 'outside',text:'外侧',type:'pie'},
        { value: 'inside',text:'内部',type:'pie'},


        { value: 'left',text:'左边',type:'funnel'},
        { value: 'right',text:'右边',type:'funnel'},
        { value: 'inside',text:'内部',type:'funnel'},
        { value: 'insideRight',text:'内部右侧',type:'funnel'},
        { value: 'insideLeft',text:'内部左侧',type:'funnel'}
    ]
    if(this.barSettings  || this.lineSettings || this.scatterSettings ||this.pictorialSettings ||this.graphSettings){
        return postion.filter(item=>{
            return item.type == 'bar'
        })
    }
    if(this.funnelSettings){
        return postion.filter(item=>{
            return item.type == 'funnel'
        })
    }
    /* if(this.lineSettings){
         return postion.filter(item=>{
             return item.type == 'line'
         })
     }*/
    if(this.pieSettings){
        return postion.filter(item=>{
            return item.type == 'pie'
        })
    }

}

/**
 * 获取自定义的json的图表类型
 */
function getSelectType(option){
    let series = option.series;
    if(!series){
        this.selectedChartType='apiUrlType'
    }else{
        let typeArr=series.map(item=>{ return item.type});
        if(typeArr.length>1){
            if(typeArr.indexOf('bar')!=-1&&typeArr.indexOf('line')!=-1){
                this.selectedChartType='mixed.linebar'
            }else if(typeArr.indexOf('bar')!=-1){
                this.selectedChartType=option.yAxis&&option.yAxis.data?'bar.multi.horizontal':'bar.multi';
            }else if(typeArr.indexOf('line')!=-1){
                this.selectedChartType='line.multi'
            }
        }else{
            let type = typeArr[0];
            switch(type){
                case 'line':this.selectedChartType='line.simple';break;
                case 'bar':this.selectedChartType='bar.simple';break;
                case 'pie' :this.selectedChartType='pie.simple';break;
                case 'funnel' :this.selectedChartType='funnel.simple';break;
                case 'gauge' :this.selectedChartType='gauge.simple';break;
                case 'scatter' :this.selectedChartType='scatter.simple';break;
                case 'pictorial.spirits' :this.selectedChartType='pictorial.spirits';break;
                default:this.selectedChartType='apiUrlType';break;
            }
        }
    }
}

/**
 * 处理数据更新图表
 * @param list
 */
function refreshChart(id, chartOptions, dataSettings, dataList){
    let seriesConfig = chartOptions['series']
    let chartType = dataSettings['chartType']
    let chartId = dataSettings['chartId']
    if(!chartType){
        vm.$Message.warning('版本升级导致数据不兼容，请删除图表重新添加!');
    }
    if( chartType === 'bar.simple' || (chartType.indexOf('line.') !== -1 && chartType!=='line.multi')){
        let { axisX, axisY } = dataSettings
        let xarray = []
        let yarray = []
        for(let item of dataList){
            xarray.push(item[axisX])
            yarray.push(item[axisY])
        }
        if(dataSettings['run']===1){
            vm.xAxisSettings.data = xarray
        }
        chartOptions['xAxis']['data'] = xarray
        seriesConfig[0].data = yarray
    }
    else if(chartType === 'scatter.simple'){
        let { axisX, axisY } = dataSettings
        let array = []
        for(let item of dataList){
            array.push([item[axisX],item[axisY]])
        }
        seriesConfig[0].data = array
    }
    else if(chartType === 'scatter.bubble'){
        let { axisX, axisY, series } = dataSettings
        let seriesMap = {}
        for(let item of dataList){
            //获取series数据
            seriesMap[item[series]] = 1
        }
        let realSeries = []
        let legendData = Object.keys(seriesMap)
        let singleSeries = seriesConfig[0]
        for(let i=0;i<legendData.length;i++){
            let name = legendData[i]
            let seriesData = []
            let temparr = dataList.filter(item=>{
                return item[series] == name
            })
            temparr.forEach(function(e){
                seriesData.push([e[axisX],e[axisY]])
            })
            let itemstyle = getSeriesItemStyle(seriesConfig, i, name)
            let obj = Object.assign({},
                singleSeries,
                itemstyle,{
                    name: name,
                    data: seriesData,
                })
            realSeries.push(obj)
        }
        if(dataSettings['run']===1){
            vm.legendSettings.data = legendData
        }
        chartOptions['legend']['data'] = legendData
        chartOptions['series'] = realSeries
    }
    else if(chartType.indexOf('pie')!=-1|| chartType === 'funnel.simple' ){
        let { axisX, axisY } = dataSettings
        let ls = [], xarray = []
        for(let item of dataList){
            let tempName = item[axisX]
            let itemStyle = getSeriesDataItemStyle(seriesConfig[0].data, tempName)
            ls.push({
                name: tempName,
                value: item[axisY],
                itemStyle: itemStyle
            })
            xarray.push(item[axisX])
        }
        if(dataSettings['run']===1){
            vm.legendSettings.data = xarray
        }
        chartOptions['legend']['data'] = xarray
        seriesConfig[0].data = ls
    }

    else if( chartType === 'pictorial.spirits'){
        let { axisX, axisY } = dataSettings
        let xarray = []
        let yarray = []
        for(let item of dataList){
            xarray.push(item[axisX])
            yarray.push(item[axisY])
        }
        if(dataSettings['run']===1){
            vm.yAxisSettings.data = xarray
        }
        chartOptions['yAxis']['data'] = xarray
        for(let item of seriesConfig){
            item['data'] = yarray
        }
    }

    else  if(chartType.indexOf('gauge.simple')!=-1){
        let { axisX, axisY } = dataSettings
        let array = []
        for(let item of dataList){
            array.push({
                name: item[axisX],
                value: item[axisY],
            })
        }
        seriesConfig[0].data = array
    }


    else if( chartType.indexOf('bar.multi')!==-1 || chartType === 'line.multi'|| chartType.indexOf('bar.stack') !== -1 ){
        let { axisX, axisY, series } = dataSettings
        let xarray = []
        let seriesMap = {}
        for(let item of dataList){
            let tempX = item[axisX]
            //获取x轴数据
            if(xarray.indexOf(tempX)<0){
                xarray.push(tempX)
            }
            //获取series数据
            seriesMap[item[series]] = 1
        }
        let realSeries = []
        let singleSeries = seriesConfig[0]
        let legendData = Object.keys(seriesMap)
        for(let i=0;i<legendData.length;i++){
            let name = legendData[i]
            let seriesData = []
            for(let x of xarray){
                let temparr = dataList.filter(item=>{
                    return item[axisX] == x && item[series] == name
                })
                if(temparr && temparr.length>0){
                    seriesData.push(temparr[0][axisY])
                }else{
                    seriesData.push(0)
                }
            }
            let itemstyle = getSeriesItemStyle(seriesConfig, i, name)
            let obj = Object.assign({},
                singleSeries,
                itemstyle,{
                    name: name,
                    data: seriesData,
                    typeData:vm.seriesTypeData
             })
            //处理堆叠情况
            if(chartType === 'bar.stack'|| chartId==='bar.negative'){
                //只有点击运行才配置
                if(!dataSettings['run']){
                    return;
                }
                let tempStack=vm.seriesTypeData.filter(item=>{ return item.name===name });
                if(tempStack[0] && tempStack[0].stack){
                    obj['stack']=tempStack[0].stack
                }else{
                    obj['stack']=''
                }
            }
            realSeries.push(obj)
        }
       if(dataSettings['run']===1){
           vm.xAxisSettings.data = xarray
           vm.legendSettings.data = legendData
        }
        if(chartType==='bar.stack.horizontal'||chartType==='bar.multi.horizontal'||chartType==='bar.negative'){
            chartOptions['yAxis']['data'] = xarray
        }else{
            chartOptions['xAxis']['data'] = xarray
        }
        chartOptions['legend']['data'] = legendData
        chartOptions['series'] = realSeries
    }
    else if(chartType === 'mixed.linebar'){
        //刷新页面的时候
        if(!dataSettings['run']){
            return;
        }
        let { axisX, axisY, series } = dataSettings
        let xarray = []
        let seriesMap = {}
        for(let item of dataList){
            let tempX = item[axisX]
            //获取x轴数据
            if(xarray.indexOf(tempX)<0){
                xarray.push(tempX)
            }
            //获取series数据
            seriesMap[item[series]] = 1
        }
        let realSeries = []
        let barSeries = util.setting2Option(vm.barSettings)?util.setting2Option(vm.barSettings):{"barWidth":15,"barMinHeight": 2,"itemStyle": {"barBorderRadius":0,"color":"" }};
        let lineSeries =  util.setting2Option(vm.lineSettings)?util.setting2Option(vm.lineSettings):{"step": false,"showSymbol": true,"smooth": false,"symbolSize":5,"lineStyle":{"width":2},"itemStyle": {"color":"" }};
        let labelSeries =  util.setting2Option(vm.seriesLabelSettings)?util.setting2Option(vm.seriesLabelSettings):{"show": true,"position": "top","textStyle": {"color": "black","fontSize": 16,"fontWeight": "bolder"}};
        let legendData = Object.keys(seriesMap)
      /*  //判断系列和图例是否有一致
        if(!isEquals(chartOptions.legend.data,legendData)){
            vm.seriesTypeData=[];
        }*/
        legendData.map(name=>{
            let seriesData = []
            //默认柱子样式 【JMREP-652】问题加label
            let singleSeries = Object.assign(barSeries,{label:labelSeries},{type:"bar"})
            for(let x of xarray){
                let temparr = dataList.filter(item=>{
                    return item[axisX] == x && item[series] == name
                })
                if(temparr && temparr.length>0){
                    seriesData.push(temparr[0][axisY])
                }else{
                    seriesData.push(0)
                }
            }
            //处理自定义系列
            if(vm.seriesTypeData.length>0){
                let series = vm.seriesTypeData.filter(item=>{
                    return item.name === name
                })
                if(series && series.length>0){
                    if(series[0].type==='line'){
                        singleSeries=Object.assign(lineSeries,{label:labelSeries},{type:'line',"yAxisIndex": 1})
                    }
                }
            }
            let obj = Object.assign({},
                singleSeries,{
                    name: name,
                    data: seriesData,
                    typeData:vm.seriesTypeData
                })
            realSeries.push(obj)
        })
        if(dataSettings['run']===1){
            vm.xAxisSettings.data = xarray
            vm.legendSettings.data = legendData
        }
        chartOptions['xAxis']['data'] = xarray
        chartOptions['legend']['data'] = legendData
        chartOptions['series'] = realSeries
    }
    else if( chartType === 'map.scatter'){
        let { axisX, axisY } = dataSettings
        let ls = [];
        let selectMap = vm.$refs.mapModal.allMapList.filter(obj=>obj.name === chartOptions['geo']['map']);
        for(let item of dataList){
            let v=[];
            if(selectMap && selectMap.length>0){
                let data=JSON.parse(selectMap[0].data);
                let mapFeature = data.features.filter(district=> district.properties.name.indexOf(item[axisX])!=-1);
                if(mapFeature && mapFeature.length>0){
                    let coordinate=mapFeature[0].properties.center;
                    let lng=coordinate[0]?coordinate[0]:coordinate.lng;
                    let lat=coordinate[1]?coordinate[1]:coordinate.lat;
                    v=[lng,lat,item[axisY]];
                    ls.push({
                        name:item[axisX],
                        value: v
                    })
                }
            }
        }
        if(ls && ls.length>0)
        seriesConfig[0].data = ls
        chartOptions['series'] = seriesConfig
    }
    //雷达图
    else if( chartType === 'radar.basic'||chartType==='radar.custom'){
        handleRadarChart(dataSettings,dataList,chartOptions)
    }
    console.info("---chartOptions---",JSON.stringify(chartOptions))
    //关系图
    if( chartType === 'graph.simple'){
        handleGraphChart(dataSettings,dataList,chartOptions)
    }
    xs.updateChart(id , chartOptions);
    if(dataSettings['run']===1){
        handleCustomData (chartOptions['series']);
        delete dataSettings['run']
        xs.updateChartExtData(id, dataSettings);
    }
}
//处理关系图图表
function handleGraphChart(dataSettings,dataList,chartOptions){
    let { axisX, axisY, series } = dataSettings;
    let seriesConfig = chartOptions['series']
    let data = dataList.data;
    let links = dataList.links;
    let seriesMap = {};
    let categories=[];
    for(let i=0,len=data.length;i<len;i++){
        //系列已存在
        if(seriesMap[data[i][series]]){
            data[i].category = seriesMap[data[i][series]];
            continue;
        }else{
            //获取series数据
            seriesMap[data[i][series]] = i;
            //获取categories数据和样式
            let singleSeries=seriesConfig[0].categories.filter(item=>item.name === data[i][series]);
            let itemStyle=singleSeries&&singleSeries.length>0?singleSeries[0]['itemStyle']:{"color": ""};
            categories.push({name:data[i][series],itemStyle:itemStyle});
            //获取data.categories为坐标
            data[i].category = i;
        }
    }

    let legendData = Object.keys(seriesMap);
    seriesConfig[0].data=data.map(item=>{return { name:item[axisX],value:item[axisY],category:item.category }});
    seriesConfig[0].links=links;
    seriesConfig[0].categories=categories;
    chartOptions['legend']['data'] = legendData
    chartOptions['series'] = seriesConfig
}

//数组对象替换的键值对
function convertKey(arr,keyMap){
    let tempString = JSON.stringify(arr);
    for(var key in keyMap){
        var reg = `/"${key}":/g`;
        tempString = tempString.replace(eval(reg),'"'+keyMap[key]+'":');
    }
    return JSON.parse(tempString);
}
//处理雷达图数据
function handleRadarChart(dataSettings,dataList,chartOptions){
    let { axisX, axisY, series } = dataSettings
    let array = []   //分类数组
    let seriesMap = {}
    let seriesConfig = chartOptions['series']
    for(let item of dataList){
        let temp = item[axisX]
        //获取x轴数据
        if(array.indexOf(temp)<0){
            array.push(temp)
        }
        //获取系列
        seriesMap[item[series]] = 1
    }
    let realSeries = []
    let realData= []
    let legendData = Object.keys(seriesMap) //新的系列data
    //雷达数据
    let indicatorData = []
    for(let i=0;i<legendData.length;i++){
        let name = legendData[i] //系列值
        let seriesData = []
        for(let x of array){
            let indicator={name:x,max:0}
            let temparr = dataList.filter(item=>{
                return item[axisX] == x && item[series] == name
            })
            if(temparr && temparr.length>0){
                seriesData.push(temparr[0][axisY])
                indicator['max']=temparr[0][axisY];
            }else{
                seriesData.push(0)
            }
            //判断取出设置数据的最大值
            let tempIndicator =indicatorData.filter(item=>{return item['name'] == x})
            if(tempIndicator && tempIndicator.length>0){
                //存在就判断大小并赋值
                if(tempIndicator[0]['max'] < indicator['max']){
                    tempIndicator[0]['max'] = indicator['max'];
                }
            }else{
                //不存在就直接push
                indicatorData.push(indicator)
            }
        }
        let singleSeries=seriesConfig[0].data.filter(item=>item.name === name);
        //雷达图的样式获取配置的
        singleSeries=singleSeries.length>0?singleSeries[0]:{}
        let obj = Object.assign({},singleSeries,{
            name: name,
            value: seriesData
        })
        realData.push(obj)
    }
    let obj = Object.assign({},{
        type: 'radar',
        data: realData
    });
    realSeries.push(obj)
    //最大值的配置获取
    let indicators=chartOptions['radar'][0]['indicator']
    //修改最大值的大小
    indicatorData.forEach(item=>{
        let ogn=indicators.filter(indicator=>indicator.name===item.name)
        item.max = ogn&&ogn.length>0?ogn[0].max:calcuMaxVal(item.max);
    })

    if(dataSettings['run']===1){
        vm.legendSettings.data = legendData
        vm.radarSettings[0].indicator = indicatorData
    }

    chartOptions['legend']['data'] = legendData
    chartOptions['radar'][0]['indicator'] = indicatorData
    chartOptions['series'] = realSeries
}
//计算雷达图边界数据
function calcuMaxVal(val){
    let first=val.toString().substr(0,1);
    first=parseInt(first)+1;
    val=first + val.toString().substr(1);
    return parseInt(val);
}

function getChartDataList(options, chartType){
    let series = options['series']
    let ls = []

    //象形图
    if(chartType=='pictorial.spirits') {
        let xdata = options['yAxis'].data
        let ydata = series[0].data
        for (let i = 0; i < xdata.length; i++) {
            ls.push({
                name: xdata[i],
                value: ydata[i]
            })
        }
    }else if(chartType=='map.scatter') {
        let data =series[0].data;
        for (let i = 0; i < data.length; i++) {
            ls.push({
                name: data[i]['name'],
                value: data[i]['value'][2]
            })
        }
    }else if(chartType.indexOf('scatter.bubble')>=0){
        for(let item of series){
            let type = item['name']
            let arr = item['data']
            for(let i=0;i<arr.length;i++){
                ls.push({
                    name: arr[i][0],
                    value: arr[i][1],
                    type: type
                })
            }
        }
    }else if(series.length>1 || chartType.indexOf('horizontal')!=-1){

        //处理条形图的情况
        let zdata = options['xAxis'].data
        if(chartType==='bar.stack.horizontal'|| chartType==='bar.multi.horizontal'|| chartType==='bar.negative'){
            zdata = options['yAxis'].data
        }
        for(let item of series){
            let type = item['name']
            let arr = item['data']
            for(let i=0;i<arr.length;i++){
                ls.push({
                    name: zdata[i],
                    value: arr[i],
                    type: type
                })
            }
        }
    }else{
        if(chartType && (chartType.indexOf('pie')>=0 || chartType.indexOf('funnel')>=0 || chartType.indexOf('gauge')>=0)){
            let sdata = series[0].data
            for(let item of sdata){
                ls.push({
                    name: item['name'],
                    value: item['value'],
                })
            }
        }else if(chartType.indexOf('scatter')>=0){
            let sdata=series[0].data;
            for(let i=0;i<sdata.length;i++){
                ls.push({
                    name: sdata[i][0],
                    value: sdata[i][1]
                })
            }
        } else if(chartType.indexOf('radar')>=0){
            //静态数据
            let sdata=series[0].data;
            let indicator=options['radar'][0]['indicator'];
            for(let i=0;i<sdata.length;i++) {
                let valueArr=sdata[i]['value'];
                for (let j = 0; j < valueArr.length; j++) {
                    ls.push({
                        name: indicator[j]['name'],
                        value: valueArr[j],
                        type: sdata[i]['name']
                    })
                }
            }
        }else if(chartType.indexOf('graph')>=0){
            //关系图静态数据
            let links=options['series'][0]['links'];
            let data=options['series'][0]['data'];
            let categories=options['series'][0]['categories'];
            data.forEach(item=>{
                item.type=categories[item.category]?categories[item.category].name:'';
            })
            for (let j = 0; j < data.length; j++) {
                ls.push({
                    name: data[j]['name'],
                    value: data[j]['value'],
                    type: data[j]['type']
                })
            }
            let str = ''
            for(let i=0;i<ls.length;i++){
                str+= "\n"+ JSON.stringify(ls[i])
                if(i<ls.length-1){
                    str+=','
                }
            }
            let str2 = ''
            for(let i=0;i<links.length;i++){
                str2+= "\n"+ JSON.stringify(links[i])
                if(i<links.length-1){
                    str2+=','
                }
            }
            return '{\n"data":['+str+'\n],\n"links":['+str2+'\n]}'
        }else{
            if(options['xAxis']){
                let xdata = options['xAxis'].data
                let ydata = series[0].data
                for(let i=0;i<xdata.length;i++){
                    ls.push({
                        name: xdata[i],
                        value: ydata[i]
                    })
                }
            }
        }
    }
    let str = ''
    for(let i=0;i<ls.length;i++){
        str+= "\n"+ JSON.stringify(ls[i])
        if(i<ls.length-1){
            str+=','
        }
    }
    return '['+str+'\n]'
}

/**
 * 添加图表 前置操作
 */
function addChartPreHandler(option) {
    let chartType = this.selectedChartType
    option['self_chart_type'] = chartType
    //象形图
    if(chartType.indexOf('pictorial')>=0){
        let symbol = option.series[0]['symbol']
        if(!symbol){
            let icon = chartType.split('.')[1]
            let pre =  window.location.origin+baseFull
            let path = `image://${pre}/jmreport/desreport_/chartsImg/pictorialIcon/${icon}.png`
            for(let item of option.series){
                item['symbol'] = path
            }
        }
    }else if(chartType.indexOf('map.scatter')>=0){
        let ls = [];
        let selectMap = mapTypeList.filter(item=>item.name === option.geo.map);
        for(let item of option.series[0].data){
            let v=[];
            if(selectMap && selectMap.length>0){
                let data = selectMap[0].data;
                let mapFeature = data.features.filter(district=> district.properties.name.indexOf(item['name'])!=-1);
                if(mapFeature && mapFeature.length>0){
                    let coordinate=mapFeature[0].properties.center;
                    let lng=coordinate[0]?coordinate[0]:coordinate.lng;
                    let lat=coordinate[1]?coordinate[1]:coordinate.lat;
                    v=[lng,lat,item['value']];
                    ls.push({
                        name:item['name'],
                        value: v
                    })
                }
            }
        }
        option.series[0]['data'] = ls
    }
}

/**
 * 获取当前Series index对应的itemstyle 适用于多柱体多折线
 * @param seriesConfig
 * @param index
 * @param name
 * @returns {{itemStyle: any}}
 */
function getSeriesItemStyle(seriesConfig, index, name) {
    let itemStyle = JSON.parse(JSON.stringify(seriesConfig[0]['itemStyle']))
    itemStyle['color'] = ''

    if(seriesConfig[index] && seriesConfig[index].name == name){
        itemStyle['color'] = seriesConfig[index]['itemStyle']['color']
    }
    return {itemStyle}
}

/**
 * 获取当前Series data中的itemstyle 适用于饼状图 漏斗图
 * @param seriesData
 * @param name
 * @returns {{color: string}}
 */
function getSeriesDataItemStyle(seriesData, name) {
    let itemStyle = {color:''}
    for(let item of seriesData){
        if(name === item.name){
            itemStyle = item['itemStyle']
            break;
        }
    }
    return itemStyle;
}

function isEquals(a, b) {
    return JSON.stringify(a.sort()) === JSON.stringify(b.sort());
}