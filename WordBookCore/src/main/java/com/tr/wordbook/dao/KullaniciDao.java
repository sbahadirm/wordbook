package com.tr.wordbook.dao;

import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.service.Md5Service;
import com.tr.wordbook.standart.BaseDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Bahad?r Memi?
 * @since 0.0.1
 */
@Repository
public class KullaniciDao extends BaseDao<Kullanici> {

    public KullaniciDao() {
        super(Kullanici.class);
    }

    public Kullanici findKullaniciByKullaniciAdi(String kullaniciAdi){
        Criteria criteria = getCurrentSession().createCriteria(Kullanici.class);
        criteria.add(Restrictions.eq("kullaniciAdi", kullaniciAdi));
        return (Kullanici) criteria.uniqueResult();
    }

    public Kullanici findKullaniciByKullaniciAdiAndSifre(String kullaniciAdi, String sifre){

        String kriptoluSifre = Md5Service.getHash(sifre);

        Criteria criteria = getCurrentSession().createCriteria(Kullanici.class);
        criteria.add(Restrictions.eq("kullaniciAdi", kullaniciAdi));
        criteria.add(Restrictions.eq("sifreKriptolu", kriptoluSifre));
        return (Kullanici) criteria.uniqueResult();
    }
}
