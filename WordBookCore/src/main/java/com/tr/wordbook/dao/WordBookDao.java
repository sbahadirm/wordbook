package com.tr.wordbook.dao;

import com.tr.wordbook.domain.Word;
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
public class WordBookDao extends BaseDao<Word> {

    public WordBookDao() {
        super(Word.class);
    }

    public List<Word> findAllWord(){
        Criteria criteria = getCurrentSession().createCriteria(Word.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<Word> findAllWordByText(String text){
        Criteria criteria = getCurrentSession().createCriteria(Word.class);
        criteria.add(Restrictions.like("text", text, MatchMode.ANYWHERE));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<Word> findAllWordByEzberlendi(EnumSecimEH ezberlendi){
        Criteria criteria = getCurrentSession().createCriteria(Word.class);
        criteria.add(Restrictions.eq("ezberlendi", ezberlendi));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    public List<Word> findAllWordByNotEzberlendi(EnumSecimEH ezberlendi){
        Criteria criteria = getCurrentSession().createCriteria(Word.class);
        criteria.add(Restrictions.ne("ezberlendi", ezberlendi));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }
}
