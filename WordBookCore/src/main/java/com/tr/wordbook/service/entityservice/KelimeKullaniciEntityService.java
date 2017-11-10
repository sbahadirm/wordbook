package com.tr.wordbook.service.entityservice;

import com.tr.wordbook.dao.KelimeKullaniciDao;
import com.tr.wordbook.dao.KullaniciDao;
import com.tr.wordbook.domain.KelimeKullanici;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.enums.EnumSecimEH;
import com.tr.wordbook.standart.BaseEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 0.0.1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KelimeKullaniciEntityService extends BaseEntityService<KelimeKullanici, KelimeKullaniciDao> {

    private final static Logger logger = LoggerFactory.getLogger(KelimeKullaniciEntityService.class);

    public KelimeKullaniciEntityService() {
        super(KelimeKullaniciDao.class);
    }

    public List<KelimeKullanici> findAllKelimeKullaniciByEzberlendi(EnumSecimEH ezberlendi){
        return getDao().findAllKelimeKullaniciByEzberlendi(ezberlendi);
    }

    public List<KelimeKullanici> findAllKelimeKullaniciByKullanici(Kullanici kullanici){
        return getDao().findAllKelimeKullaniciByKullanici(kullanici);
    }

    public List<KelimeKullanici> findAllKelimeByNotEzberlendi(EnumSecimEH ezberlendi){
        return getDao().findAllKelimeByNotEzberlendi(ezberlendi);
    }

    public List<KelimeKullanici> findAllKelimeKullaniciByNotEzberlendiAndKullanici(EnumSecimEH ezberlendi, Kullanici kullanici){
        return getDao().findAllKelimeKullaniciByNotEzberlendiAndKullanici(ezberlendi, kullanici);
    }

}