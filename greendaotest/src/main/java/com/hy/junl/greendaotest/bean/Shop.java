package com.hy.junl.greendaotest.bean;

import com.hy.junl.greendaotest.help.MyObjectConverent;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * =============================================
 * 作    者：Junl(袁军亮)
 * 描    述：
 * 创建日期：2017/10/26
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * * @Entity：将我们的java普通类变为一个能够被greenDAO识别的数据库类型的实体类;
 *
 * @nameInDb：在数据库中的名字，如不写则为实体中类名；
 * @Id：选择一个long / Long属性作为实体ID。 在数据库方面，它是主键。 参数autoincrement是设置ID值自增；
 * @NotNull：使该属性在数据库端成为“NOT NULL”列。 通常使用@NotNull标记原始类型（long，int，short，byte）是有意义的；
 * @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化。
 * =============================================
 */

@Entity
public class Shop {
    public static final int TYPE_LOVE = 0x02;
    //不能用int  GreenDao的主键必须设置成包装类 Long , 大写L  参考：http://blog.csdn.net/sunsteam/article/details/52634945
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String name;
    @Property(nameInDb = "price")
    private String price;
    private int sell_num;
    private String image_url;
    private String address;
    private int type;
    @Convert(/**指定转换器 **/converter = MyObjectConverent.class,/**指定数据库中的列字段**/columnType =String.class )
    private List<Apple> appleList;
    @Generated(hash = 1880070938)
    public Shop(Long id, String name, String price, int sell_num, String image_url, String address,
            int type, List<Apple> appleList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sell_num = sell_num;
        this.image_url = image_url;
        this.address = address;
        this.type = type;
        this.appleList = appleList;
    }
    @Generated(hash = 633476670)
    public Shop() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getSell_num() {
        return this.sell_num;
    }
    public void setSell_num(int sell_num) {
        this.sell_num = sell_num;
    }
    public String getImage_url() {
        return this.image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<Apple> getAppleList() {
        return this.appleList;
    }
    public void setAppleList(List<Apple> appleList) {
        this.appleList = appleList;
    }

}
