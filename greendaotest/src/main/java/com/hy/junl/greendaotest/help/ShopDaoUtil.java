package com.hy.junl.greendaotest.help;

import com.hy.junl.greendaotest.base.BaseApplication;
import com.hy.junl.greendaotest.bean.Shop;
import com.hy.junl.greendaotest.bean.dao.ShopDao;

import java.util.List;

/**JudgeNullUtil
 * =============================================
 * 作    者：Junl(袁军亮)
 * 描    述：
 * <p>
 * 创建日期：2017/10/26
 * 文艺青年：人生若只如初见，何事秋风悲画扇。
 * =============================================
 */

public class ShopDaoUtil {

    public ShopDaoUtil() {
    }

    /**
     * 新增数据
     * @param shop
     */
    public boolean insert(Shop shop) {
        boolean flag;
        flag = BaseApplication.getDaoSession().getShopDao().insert(shop) == -1 ? false : true;
        return flag;
    }


    public List<Shop> queryShop() {
        List<Shop> list = BaseApplication.getDaoSession().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
        return list;
    }

    public void deleteShop(long id) {
        BaseApplication.getDaoSession().getShopDao().deleteByKeyInTx(id);
    }

    public void updateShop(Shop shop) {
        BaseApplication.getDaoSession().getShopDao().update(shop);
    }
}
