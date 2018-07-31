package au.com.wytn.centus.controllers.api.core.users

import au.com.wytn.centus.domains.core.users.UserRole
import org.apache.commons.lang3.StringUtils
import org.springframework.validation.{Errors, Validator}

class UsersRoleValidator extends Validator {

    override def supports(clazz: Class[_]): Boolean = classOf[UserRole] == clazz

    override def validate(target: scala.Any, errors: Errors): Unit = {
        val userRole = target.asInstanceOf[UserRole]

        Option(StringUtils.trimToNull(userRole.getName)) match {
            case None => errors.rejectValue("name", "Empty Name")
            case Some(name) =>
                if (name.length > UserRole.NAME_MAX_LENGTH) {
                    errors.rejectValue("name", "Name Max Length is 100")
                }
        }
    }
}
