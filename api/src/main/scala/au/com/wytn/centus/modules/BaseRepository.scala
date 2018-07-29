package au.com.wytn.centus.modules

import au.com.wytn.centus.domains.Model
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
trait BaseRepository[T <: Model, ID <: java.io.Serializable]
    extends MongoRepository[T, ID]
    with QuerydslPredicateExecutor[T] {

}
