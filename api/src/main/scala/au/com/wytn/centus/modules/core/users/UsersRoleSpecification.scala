package au.com.wytn.centus.modules.core.users

import au.com.wytn.centus.domains.core.users.QUserRole
import com.querydsl.core.types.dsl.BooleanExpression
import org.bson.types.ObjectId

object UsersRoleSpecification {

    def idCondition(id: String): BooleanExpression = QUserRole.userRole.id.eq(id)

    def actualCondition: BooleanExpression = QUserRole.userRole.archivedAt.isNull
}
