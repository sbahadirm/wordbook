package com.tr.wordbook.service.entityservice;

import com.tr.wordbook.dao.KelimeDao;
import com.tr.wordbook.domain.Kullanici;
import com.tr.wordbook.domain.Kelime;
import com.tr.wordbook.enums.EnumSecimEH;
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
public class KelimeEntityService extends BaseEntityService<Kelime, KelimeDao> {

    private final static Logger logger = LoggerFactory.getLogger(KelimeEntityService.class);

    public KelimeEntityService() {
        super(KelimeDao.class);
    }

    public List<Kelime> findAllWordByText(String text){
        return getDao().findAllKelimeByText(text);
    }

    public List<Kelime> findAllWord(){
        return getDao().findAllKelime();
    }

    public List<Kelime> findAllWordByEzberlendi(EnumSecimEH ezberlendi){
        return getDao().findAllKelimeByEzberlendi(ezberlendi);
    }

    public List<Kelime> findAllWordByNotEzberlendi(EnumSecimEH ezberlendi){
        return getDao().findAllKelimeByNotEzberlendi(ezberlendi);
    }

    public List<Kelime> findAllWordByNotEzberlendiAndKullanici(EnumSecimEH ezberlendi, Kullanici kullanici){
        return getDao().findAllKelimeByNotEzberlendiAndKullanici(ezberlendi, kullanici);
    }
}
