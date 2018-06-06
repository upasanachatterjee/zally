package de.zalando.zally.rule.zalando

import de.zalando.zally.rule.Context
import de.zalando.zally.rule.api.Check
import de.zalando.zally.rule.api.Rule
import de.zalando.zally.rule.api.Severity
import de.zalando.zally.rule.api.Violation

@Rule(
    ruleSet = VidaRuleSet::class,
    id = "APIGuidelines-ResourceEndpoints",
    severity = Severity.MUST,
    title = "Provide acceptable API endpoint"
)
class ApiEndpointRule {
    private val apiIdPattern = """^[a-z0-9][a-z0-9-:.]{6,62}[a-z0-9]$""".toRegex()

    private val noApiIdDesc = "API Identifier should be provided"
    private val invalidApiIdDesc = "API Identifier doesn't match the pattern $apiIdPattern"

    private val extensionName = "x-api-id"

    @Check(severity = Severity.SHOULD)
    fun validate(context: Context): Violation? {
        val apiId = context.api.info?.extensions?.get(extensionName)

        return when {
            apiId == null || apiId !is String -> context.violation(noApiIdDesc)
            !apiId.matches(apiIdPattern) -> context.violation(invalidApiIdDesc)
            else -> null
        }
    }
}


// COMMENT OUT THIS FOLLOWING PORTION BEFORE BUILDING SERVER
// UNFINISHED, AND WILL ERROR

class ApiEndpointRule {

// CHECK THAT VERSION EXISTS
    @Check(severity = Severity.MUST)
    fun validate(swagger: Swagger): Violation? {
        val hasVersionInInfo = swagger.info != null && PatternUtil.hasVersionInInfo(swagger.info)
        // TODO: update PatternUtil.java and figure out where the swagger in fun validate(swagger: Swagger) comes from
        // TODO: 
    }
}