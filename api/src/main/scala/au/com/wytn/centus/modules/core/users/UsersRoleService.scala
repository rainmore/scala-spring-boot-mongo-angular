package au.com.wytn.centus.modules.core.users

import au.com.wytn.centus.domains.core.users.UserRole
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
    import UsersRoleSpecification._

    def findOne(id: String): Option[UserRole] = repository.findById(id).asScala

    def findActualOne(id: String): Option[UserRole] = repository.findOne(idCondition(id) and actualCondition).asScala

    def find(pageable: Pageable): Page[UserRole] = repository.findAll(pageable)

    def findActual(pageable: Pageable): Page[UserRole] = repository.findAll(actualCondition, pageable)

    @Transactional
    def save(userRole: UserRole): UserRole = repository.save(userRole)

    @Transactional
    def delete(userRole: UserRole): Unit = repository.delete(userRole)

    @Transactional
    def archive(userRole: UserRole): Unit = {
        userRole.archive()
        save(userRole)
    }
}
