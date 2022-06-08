package com.sinonc.orders.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 收货地址表 od_address
 *
 * @author sinonc
 * @date 2019-07-26
 */
@ApiModel("收货地址实体")
public class Address {

    private static final long serialVersionUID = 1L;

    /** 主键id自增 */
    @ApiModelProperty(value = "地址ID",hidden = true,required = true,example = "1")
    private Long addressId;
    /** 会员id */
    @ApiModelProperty(value = "会员ID",example = "1",required = true)
    private Long memberIdP;
    /** 收货人姓名 */
    @NotEmpty(message="收货人姓名不能为空！")
    @Size(max=10, min=2,message = "收货人姓名长度须在2到10之间")
    @ApiModelProperty(value = "收货人姓名",example = "张三",required = true)
    private String name;
    /** 手机号码 */
    @Size(min = 11, max = 11, message = "手机号格式不对")
    @ApiModelProperty(value = "手机号",example = "13333333333",required = true)
    private String phone;
    /** 省 */
    @ApiModelProperty(value = "省份",example = "江西省",required = true)
    private String province;
    /** 市 */
    @ApiModelProperty(value = "城市",example = "赣州市",required = true)
    private String city;
    /** 区县 */
    @ApiModelProperty(value = "区县",example = "赣县区",required = true)
    private String county;
    /** 详细地址 */
    @NotEmpty(message="收货地址不能为空！")
    @ApiModelProperty(value = "街道门牌号",example = "赣新大道129号",required = true)
    private String addr;
    /** 创建时间 */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /** 修改时间 */
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    /** 是否删除 */
    @ApiModelProperty(hidden = true)
    private Integer delFlag;

    private Integer defaults;

    private String memberName;

    @ApiModelProperty(value = "地址合并名称")
    private String mergerName;


    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setAddressId(Long addressId) {
            this.addressId = addressId;
    }

    public Long getAddressId() {
            return addressId;
    }
    public void setMemberIdP(Long memberIdP) {
            this.memberIdP = memberIdP;
    }

    public Long getMemberIdP() {
            return memberIdP;
    }
    public void setName(String name) {
            this.name = name;
    }

    public String getName() {
            return name;
    }
    public void setPhone(String phone) {
            this.phone = phone;
    }

    public String getPhone() {
            return phone;
    }
    public void setProvince(String province) {
            this.province = province;
    }

    public String getProvince() {
            return province;
    }
    public void setCity(String city) {
            this.city = city;
    }

    public String getCity() {
            return city;
    }
    public void setCounty(String county) {
            this.county = county;
    }

    public String getCounty() {
            return county;
    }
    public void setAddr(String addr) {
            this.addr = addr;
    }

    public String getAddr() {
            return addr;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setDelFlag(Integer delFlag) {
            this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
            return delFlag;
    }

    public Integer getDefaults() {
        return defaults;
    }

    public void setDefaults(Integer defaults) {
        this.defaults = defaults;
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("addressId",getAddressId())
                .append("memberIdP",getMemberIdP())
                .append("name",getName())
                .append("phone",getPhone())
                .append("province",getProvince())
                .append("city",getCity())
                .append("county",getCounty())
                .append("addr",getAddr())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .append("delFlag",getDelFlag())
                .toString();
    }
}
