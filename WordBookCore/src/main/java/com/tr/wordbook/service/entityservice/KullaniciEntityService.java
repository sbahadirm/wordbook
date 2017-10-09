package com.tr.wordbook.service.entityservice;

import com.tr.wordbook.dao.KullaniciDao;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.standart.BaseEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KullaniciEntityService extends BaseEntityService<Kullanici, KullaniciDao> {

    private final static Logger logger = LoggerFactory.getLogger(KullaniciEntityService.class);

    public KullaniciEntityService() {
        super(KullaniciDao.class);
    }

    public Kullanici findKullaniciByKullaniciAdi(String kullaniciAdi){
        return getDao().findKullaniciByKullaniciAdi(kullaniciAdi);
    }

    public Kullanici findKullaniciByKullaniciAdiAndSifre(String kullaniciAdi, String sifre){
        return getDao().findKullaniciByKullaniciAdiAndSifre(kullaniciAdi, sifre);
    }
}
