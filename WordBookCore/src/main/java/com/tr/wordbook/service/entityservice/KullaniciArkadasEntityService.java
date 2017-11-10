package com.tr.wordbook.service.entityservice;

import com.tr.wordbook.dao.KullaniciArkadasDao;
import com.tr.wordbook.dao.KullaniciDao;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.KullaniciArkadas;
import com.tr.wordbook.standart.BaseEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KullaniciArkadasEntityService extends BaseEntityService<KullaniciArkadas, KullaniciArkadasDao> {

    private final static Logger logger = LoggerFactory.getLogger(KullaniciArkadasEntityService.class);

    public KullaniciArkadasEntityService() {
        super(KullaniciArkadasDao.class);
    }

    public List<KullaniciArkadas> findAllKullaniciArkadasByKullanici(Kullanici kullanici){
        return getDao().findAllKullaniciArkadasByKullanici(kullanici);
    }

}
