package au.com.wytn.centus.modules.core.users

import au.com.wytn.centus.domains.core.users.UserRole
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import scala.compat.java8.OptionConverters._

@Service
class UsersRoleService @Autowired
(
    repository: UsersRoleRepository
) {

    def findOne(id: ObjectId): Option[UserRole] = repository.findById(id).asScala

    def find(pageable: Pageable): Page[UserRole] = repository.findAll(pageable)

    @Transactional
    def save(userRole: UserRole): UserRole = repository.save(userRole)

    def delete(userRole: UserRole): Unit = repository.delete(userRole)
}
