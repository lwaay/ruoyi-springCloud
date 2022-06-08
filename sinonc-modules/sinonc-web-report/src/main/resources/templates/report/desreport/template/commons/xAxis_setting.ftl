<script type="text/x-template" id="xAxis-setting-template">
    <Submenu  name="11" style="border-bottom: inset 1px;">
        <template slot="title">
            X轴设置
        </template>
        <div class="blockDiv" style="padding-bottom: 10px">
            <Row class="ivurow">
                <p>显示&nbsp;&nbsp;</p>
                <i-switch size="small" style="margin-left: 105px;" v-model="xAxisOption.show" @on-change="onXAxisChange"/>
            </Row>
            <Row class="ivurow">
                <p>X轴名称&nbsp;&nbsp;</p>
                <i-input size="small" v-model="xAxisOption.name" @on-blur="onXAxisChange" style="width: 111px;"></i-input>
            </Row>
            <Row class="ivurow">
                <p>分隔线&nbsp;&nbsp;</p>
                <i-switch size="small" style="margin-left: 90px;" v-model="xAxisOption.splitLine_show" @on-change="onXAxisChange"/>
            </Row>
            <Row class="ivurow" v-if="typeof xAxisOption.splitLine_show !== 'undefined'">
                <p>颜色设置&nbsp;&nbsp;</p>
                <Col>
                <color-picker class="colorPicker" :transfer="true" v-model="xAxisOption.splitLine_lineStyle_color" size="small" @on-change="onXAxisChange"/>
                </Col>
            </Row>
            <Row class="ivurow">
                <p>文字角度&nbsp;&nbsp;</p>
                <slider v-model="xAxisOption.axisLabel_rotate" @on-change="onXAxisChange" style="margin-top: -9px;width: 120px;margin-left: 5px;"></slider>
            </Row>
            <Row class="ivurow">
                <p>字体大小&nbsp;&nbsp;</p>
                <i-input size="small" type="number" v-model="xAxisOption.axisLabel_textStyle_fontSize" @on-blur="onXAxisChange" style="width: 111px;"></i-input>
            </Row>
            <Row class="ivurow" v-if="typeof xAxisOption.axisLabel_textStyle_color !== 'undefined'">
                <p>字体颜色&nbsp;&nbsp;</p>
                <Col>
                <color-picker class="colorPicker" :transfer="true" size="small" v-model="xAxisOption.axisLabel_textStyle_color" @on-change="onXAxisChange"/>
                </Col>
            </Row>

            <Row class="ivurow" v-if="typeof xAxisOption.axisLine_lineStyle_color !== 'undefined'">
                <p>轴线颜色&nbsp;&nbsp;</p>
                <Col>
                <color-picker class="colorPicker" :transfer="true" size="small" v-model="xAxisOption.axisLine_lineStyle_color" @on-change="onXAxisChange"/>
                </Col>
            </Row>
        </div>
    </Submenu>
</script>
<script>
    Vue.component('j-xaxis-setting', {
        template: '#xAxis-setting-template',
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
                xAxisOption: {
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
                    this.xAxisOption = Object.assign(this.xAxisOption, this.settings)
                }
            },
            onXAxisChange(){
                this.$emit('change','xAxis', this.xAxisOption)
            }
        }
    })
</script>