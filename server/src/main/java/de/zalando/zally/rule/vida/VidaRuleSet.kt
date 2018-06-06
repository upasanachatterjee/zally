package de.zalando.zally.rule.zalando

import de.zalando.zally.rule.AbstractRuleSet
import de.zalando.zally.rule.api.Rule
import org.springframework.stereotype.Component
import java.net.URI

@Component
class VidaRuleSet : AbstractRuleSet() {
    override val id: String = javaClass.simpleName
    override val title: String = "VIDA API Guidelines"
    override val url: URI = URI.create("https://vidahealth.atlassian.net/wiki/spaces/WIKI/pages/297730082/API+Guidelines")
    override fun url(rule: Rule): URI {
        return url.resolve("#${rule.id}")
    }
}
