package com.tr.wordbook.dao;

import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.KullaniciArkadas;
import com.tr.wordbook.service.Md5Service;
import com.tr.wordbook.standart.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Repository
public class KullaniciArkadasDao extends BaseDao<KullaniciArkadas> {

    public KullaniciArkadasDao() {
        super(KullaniciArkadas.class);
    }

    public List<KullaniciArkadas> findAllKullaniciArkadasByKullanici(Kullanici kullanici){
        Criteria criteria = getCurrentSession().createCriteria(KullaniciArkadas.class);
        criteria.add(Restrictions.eq("kullanici.id", kullanici.getId()));
        return criteria.list();
    }

}
