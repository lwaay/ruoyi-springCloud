<script type="text/x-template" id="scatter-setting-template">
    <div >
        <Submenu  name="9" style="border-bottom: inset 1px;" >
            <template slot="title">
                散点设置
            </template>
            <div class="blockDiv" style="padding-bottom: 10px">
                <Row class="ivurow">
                    <p>大小&nbsp;&nbsp;</p>
                    <slider v-model="scatterOption.symbolSize" @on-change="onScatterChange" style="margin-top: -9px;width: 120px;margin-left: 5px;"></slider>
                </Row>
                <Row class="ivurow" v-if="typeof scatterOption.itemStyle_color !== 'undefined'">
                    <p>颜色&nbsp;&nbsp;</p>
                    <Col><color-picker class="colorPicker" :transfer="true"  size="small" v-model="scatterOption.itemStyle_color" @on-change="onScatterChange"/></Col>
                </Row>
                <Row class="ivurow">
                    <p style="margin-bottom: 10px;">透明度&nbsp;&nbsp;</p>
                    <slider max="1" step="0.1" v-model="scatterOption.itemStyle_opacity" @on-change="onScatterChange" style="margin-top: -9px;width: 110px;margin-left: 5px;" ></slider>
                </Row>
            </div>
        </Submenu>
    </div>
</script>
<script>
    Vue.component('j-scatter-setting', {
        template: '#scatter-setting-template',
        props: {
            settings: {
                type: Object,
                required: true,
                default: () => {
                }
            }
        },
        data(){
            return {
                scatterOption: {
                    itemStyle_color:"#C23531"
                }
            }
        },
        watch: {
            settings: {
                deep: true,
                immediate: true,
                handler: function (){
                    this.initData()
                }
            }
        },
        methods: {
            initData: function (){
                if (this.settings){
                    this.scatterOption = Object.assign(this.scatterOption, this.settings)
                }
            },
            onScatterChange(){
                this.$emit('change',this.scatterOption)
            }
        }
    })
</script>