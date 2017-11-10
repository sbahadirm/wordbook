package com.tr.wordbook.dao;

import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.Kelime;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.standart.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@Repository
public class KelimeDao extends BaseDao<Kelime> {

    public KelimeDao() {
        super(Kelime.class);
    }

    public List<Kelime> findAllKelime(){
        Criteria criteria = getCurrentSession().createCriteria(Kelime.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<Kelime> findAllKelimeByText(String text){
        Criteria criteria = getCurrentSession().createCriteria(Kelime.class);
        criteria.add(Restrictions.like("text", text, MatchMode.ANYWHERE));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<Kelime> findAllKelimeByEzberlendi(EnumSecimEH ezberlendi){
        Criteria criteria = getCurrentSession().createCriteria(Kelime.class);
        criteria.add(Restrictions.eq("ezberlendi", ezberlendi));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<Kelime> findAllKelimeByNotEzberlendi(EnumSecimEH ezberlendi){
        Criteria criteria = getCurrentSession().createCriteria(Kelime.class);
        criteria.add(Restrictions.ne("ezberlendi", ezberlendi));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<Kelime> findAllKelimeByNotEzberlendiAndKullanici(EnumSecimEH ezberlendi, Kullanici kullanici){
        Criteria criteria = getCurrentSession().createCriteria(Kelime.class)
                .add(Restrictions.ne("ezberlendi", ezberlendi))
                .add(Restrictions.eq("kullanici.id", kullanici))
                .addOrder(Order.asc("id"));
        return criteria.list();
    }
}
