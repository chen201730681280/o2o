package com.imooc.o2o.enums;
/*枚举类的好处：
    1.枚举不可以用set方法，杜绝在代码中强行set的写法
    2.static final扩展性较差，枚举的每一个属性我们都可以扩展属性，重写方法等等。
    3.可以通过switch去判断，代码的简洁性更高
   */
public enum ShopStateEnum {
    /*
    * 枚举类的属性要大写，用逗号隔开，
    * 使用的时候可以直接类.属性就可以访问
    * 这时的返回值其实是一个类，在枚举类里其实是定义了一个又一个的新类
    *
    * 方法：
    *   - name()，toString()，就是枚举类中定义的属性的名称
    *   - ordinal()，就是枚举类中对应属性的位置，排在第几位，从0开始
    *   - compareTo()，就是用前面的一个枚举的ordinal去减后面的ordinal，返回int
    * */
    CHECK(0, "审核中"),
    OFFLINE(-1, "非法店铺"),
    SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"),
    INNER_ERROR(-1001, "内部系统错误"),
    NULL_SHOPID(-1002, "ShopId为空"),
    NULL_SHOP(-1003,"Shop信息为空");
    private int state;
    private String stateInfo;

    /**
     * 构造器
     * 只能用private修饰
     * @param state
     * @param stateInfo
     */
    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 一句传入的state返回相应的enum的值
     */
    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

}
