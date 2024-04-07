package hk.edu.hkbu.ftproject.security;

import hk.edu.hkbu.ftproject.entity.HisQuote;
import hk.edu.hkbu.ftproject.entity.key.HisQuoteCompKey;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "AnonymousRestRole", code = AnonymousRestRole.CODE, scope = "API")
public interface AnonymousRestRole {

    String CODE = "anonymous-rest-role";

    @EntityAttributePolicy(entityClass = HisQuote.class,
            attributes = "*",
            action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = HisQuote.class,
            actions = {EntityPolicyAction.CREATE, EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void hisQuote();

    @EntityAttributePolicy(entityClass = HisQuoteCompKey.class,
            attributes = "*",
            action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = HisQuoteCompKey.class,
            actions = {EntityPolicyAction.CREATE, EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void hisQuoteCompKey();
}