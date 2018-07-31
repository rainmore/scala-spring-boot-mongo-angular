package au.com.wytn.centus.controllers.api.core.users

import au.com.wytn.centus.controllers.BaseController
import au.com.wytn.centus.domains.EntityNotFoundException
import au.com.wytn.centus.domains.core.users.UserRole
import au.com.wytn.centus.modules.core.users.UsersRoleService
import javax.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.web.bind.WebDataBinder
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

    @InitBinder(Array("role"))
    private def initValidatorBinder(binder: WebDataBinder): Unit = {
        binder.addValidators(new UsersRoleValidator)
    }

    @GetMapping(Array(URL_LIST))
    def list(pageable: Pageable): Page[UserRole] = {
        service.findActual(pageable)
    }

    @GetMapping(Array(URL_ITEM))
    def findOne(@PathVariable id: String): UserRole = {
        service.findActualOne(id).getOrElse(throw new EntityNotFoundException)
    }

    @PostMapping(Array(URL_LIST))
    def add(@Valid @RequestBody role: UserRole): UserRole = {
        service.save(role)
    }

    @PutMapping(Array(URL_ITEM))
    def update(@PathVariable id: String, @Valid @RequestBody role: UserRole): UserRole = {
        service.findOne(id).map(userRole => {
            userRole.setName(role.getName)
            userRole.setActive(role.isActive)
            service.save(userRole)
        }).getOrElse(throw new EntityNotFoundException)
    }

    @DeleteMapping(Array(URL_ITEM))
    def delete(@PathVariable id: String): Unit = {
        service.findActualOne(id) match {
            case None => throw new EntityNotFoundException
            case Some(userRole) => service.archive(userRole)
        }
    }
}
