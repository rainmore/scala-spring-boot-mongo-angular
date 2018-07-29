package au.com.wytn.centus.controllers.api.core.users

import au.com.wytn.centus.controllers.BaseController
import au.com.wytn.centus.domains.core.users.UserRole
import au.com.wytn.centus.modules.core.users.UsersRoleService
import javax.validation.Valid
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.web.bind.annotation._

object UsersRoleController {
    final val URL_LIST          = "/api/core/users/roles"
    final val URL_ALL           = "/api/core/users/roles/all"
    final val URL_ITEM          = "/api/core/users/roles/{id}"
    final val URL_EMPTY         = "/api/core/users/roles/empty"
    final val URL_TOGGLE_ACTIVE = "/api/core/users/roles/{id}/toggle-activate"
}

@RestController
class UsersRoleController @Autowired
(
    service: UsersRoleService
) extends BaseController {

    import UsersRoleController._

    @GetMapping(Array(URL_LIST))
    def list(pageable: Pageable): Page[UserRole] = {
        service.find(pageable)
    }

    @GetMapping(Array(URL_ITEM))
    def findOne(@PathVariable id: ObjectId): Option[UserRole] = {
        service.findOne(id)
    }

    @PostMapping(Array(URL_LIST))
    def add(@Valid @RequestBody role: UserRole): Option[UserRole] = {
        Some(service.save(role))
    }

    @PutMapping(Array(URL_ITEM))
    def update(@PathVariable id: ObjectId, @Valid @RequestBody role: UserRole): Option[UserRole] = {
        service.findOne(id).map(userRole => {
            userRole.setName(role.getName)
            userRole.setActive(role.isActive)

            service.save(userRole)
        })
    }

    @DeleteMapping(Array(URL_ITEM))
    def delete(@PathVariable id: ObjectId): Unit = {
        service.findOne(id).foreach(userRole => {
            service.delete(userRole)
        })
    }
}
