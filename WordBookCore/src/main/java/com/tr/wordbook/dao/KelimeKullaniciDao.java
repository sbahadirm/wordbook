package com.tr.wordbook.dao;

import com.tr.wordbook.domain.KelimeKullanici;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.standart.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Repository
public class KelimeKullaniciDao extends BaseDao<KelimeKullanici> {

    public KelimeKullaniciDao() {
        super(KelimeKullanici.class);
    }

    public List<KelimeKullanici> findAllKelimeKullaniciByEzberlendi(EnumSecimEH ezberlendi){
        Criteria criteria = getCurrentSession().createCriteria(KelimeKullanici.class);
        criteria.add(Restrictions.eq("ezberlendi", ezberlendi));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<KelimeKullanici> findAllKelimeKullaniciByKullanici(Kullanici kullanici){
        Criteria criteria = getCurrentSession().createCriteria(KelimeKullanici.class);
        criteria.add(Restrictions.eq("kullanici.id", kullanici.getId()));
        return criteria.list();
    }

    public List<KelimeKullanici> findAllKelimeByNotEzberlendi(EnumSecimEH ezberlendi){
        Criteria criteria = getCurrentSession().createCriteria(KelimeKullanici.class);
        criteria.add(Restrictions.ne("ezberlendi", ezberlendi));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<KelimeKullanici> findAllKelimeKullaniciByNotEzberlendiAndKullanici(EnumSecimEH ezberlendi, Kullanici kullanici){
        Criteria criteria = getCurrentSession().createCriteria(KelimeKullanici.class)
                .add(Restrictions.ne("ezberlendi", ezberlendi))
                .add(Restrictions.eq("kullanici.id", kullanici.getId()))
                .addOrder(Order.asc("id"));
        return criteria.list();
    }
}
