package com.elcom.data.interview.repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import com.elcom.data.BaseRepository;
import com.elcom.data.interview.entity.Rating;
import com.elcom.data.repository.IUpsertRepository;
import com.elcom.data.user.entity.User;

public class CommonRepository extends BaseRepository implements IUpsertRepository<User> {

    public CommonRepository(Session session) {
        super(session);
    }

    public boolean insertRating(Rating item) {
        @SuppressWarnings("rawtypes")
        NativeQuery query = this.session.getNamedNativeQuery("insertRating")
                .setParameter("job_id", item.getJobId())
                .setParameter("user_id", item.getUserId())
                .setParameter("note", item.getNote())
                .setParameter("rating", item.getRating());

        return query.executeUpdate() >= 1;
    }

    public List<Rating> findAllCareer() {
        return this.session.createQuery("from Rating order by name", Rating.class).list();
    }

    @Override
    public void upsert(User item) {
    }
}
