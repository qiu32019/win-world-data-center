package com.ytx.center.server.extension.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum LotteryTypeEnum implements IEnum<String> {
    BJPK10(1, "北京PK10"),
    JSSC(2, "极速赛车"),
    CQHLSX(3, "重庆欢乐生肖"),
    XYSSC(4, "幸运时时彩"),
    XYFT(5, "幸运飞艇"),
    SGFT(6, "SG飞艇"),
    XGC(7, "香港彩"),
    XYFFC(8, "幸运分分彩"),
    HL5FC(9, "河内5分彩"),
    TW5FC(10, "台湾5分彩"),
    JSSSC(11, "极速时时彩"),
    TJSSC(12, "天津时时彩"),
    XJSSC(13, "新疆时时彩"),
    GDKL10F(14, "广东快乐十分"),
    GD11X5(15, "广东11选5"),
    JSK3(16, "江苏快3"),
    SYYDJ(17, "十一运夺金"),
    CQXYNC(18, "重庆幸运农场"),
    AZXY5(19, "澳洲幸运5"),
    AZXY8(20, "澳洲幸运8"),
    AZXY10(21, "澳洲幸运10"),
    AZXY20(22, "澳洲幸运20"),
    BJKL8(23, "北京快乐8"),
    JX11X5(24, "江西11选5"),
    JS11X5(25, "江苏11选5"),
    AH11X5(26, "安徽11选5"),
    SH11X5(27, "上海11选5"),
    LL11X5(28, "辽宁11选5"),
    HB11X5(29, "湖北11选5"),
    GX11X5(30, "广西11选5"),
    JL11X5(31, "吉林11选5"),
    NMG11X5(32, "内蒙古11选5"),
    ZJ11X5(33, "浙江11选5"),
    GXK3(34, "广西快3"),
    JLK3(35, "吉林快3"),
    HBK3(36, "河北快3"),
    NMGK3(37, "内蒙古快3"),
    AHK3(38, "安徽快3"),
    FJK3(39, "福建快3"),
    HBK3_01(40, "湖北快3"),
    BJK3(41, "北京快3"),
    TJKL10F(42, "天津快乐十分"),
    JSFT(43, "极速飞艇"),
    GXKL10F(44, "广西快乐十分"),
    FCSSQ(45, "福彩双色球"),
    CJDLT(46, "超级大乐透"),
    FC3D(47, "福彩3D"),
    FC7LC(48, "福彩七乐彩"),
    TCPL3(49, "体彩排列3"),
    TCPL5(50, "体彩排列5"),
    TC7XC(51, "体彩七星彩"),
    JS6HC(52, "极速六合彩"),
    JSK3_01(53, "极速快3"),
    JSKL10F(54, "极速快乐十分"),
    JSKL8(55, "极速快乐8"),
    JS11X5_01(56, "极速11选5"),
    PCDDXY28(57, "pc蛋蛋幸运28"),
    TWBG(58, "台湾宾果"),
    SHK3(59, "上海快3"),
    GZK3(60, "贵州快3"),
    CQSSC(61,"重庆时时彩"),
    BJSSC(62,"北京时时彩");

    private final int code;

    private final String descp;

    LotteryTypeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }

    @Override
    public String getValue() {
        return name();
    }
}
