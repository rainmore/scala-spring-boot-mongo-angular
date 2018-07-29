package au.com.wytn.centus.modules.core.users

import au.com.wytn.centus.domains.core.users.UserRole
import au.com.wytn.centus.modules.BaseRepository
import org.bson.types.ObjectId

trait UsersRoleRepository extends BaseRepository[UserRole, ObjectId] {

}
