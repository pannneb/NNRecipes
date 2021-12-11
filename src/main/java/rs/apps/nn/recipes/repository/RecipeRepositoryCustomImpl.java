package rs.apps.nn.recipes.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.apps.nn.recipes.domain.Word;

@Repository
@Transactional(readOnly = true)
public class RecipeRepositoryCustomImpl implements RecipeRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    //	@Autowired
	//	private SessionFactory sessionFactory;
	// 
	//
	//	public void setSessionFactory(SessionFactory sf) {
	//		this.sessionFactory = sf;
	//	}

	//    List<?> movies = em.createQuery("SELECT movie from Movie movie where movie.language = ?1")
	//      .setParameter(1, "English")
	//      .getResultList();
    
    @Override
	public Optional<Word> searchWordByWordDataHQL(String word) {
		Query query = entityManager.createQuery("SELECT w FROM Word w where w.word =?1 " )
				.setParameter(1, word);
        return Optional.ofNullable((Word)query.getSingleResult());// query.getResultList();
	}

	@Override
	public Optional<Word> searchDataByCustomVal(String word) {
		Query query = entityManager.createNativeQuery("SELECT w.* FROM asocijacije.word as w " +
                "WHERE w.word LIKE ?", Word.class);
        query.setParameter(1,  "%" + word + "%");
        return Optional.ofNullable((Word)query.getSingleResult());// query.getResultList();
		
		
		
		// TODO Auto-generated method stub
		//return null;
		

//		Session session = this.sessionFactory.getCurrentSession();
//		Optional<Word> wordRet = (Word) session.get(Word.class, word);
//		return wordRet;
	}


}


