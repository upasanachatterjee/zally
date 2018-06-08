package de.zalando.zally.rule.zalando
import de.zalando.zally.rule.Context
import de.zalando.zally.rule.api.Check
import de.zalando.zally.rule.api.Rule
import de.zalando.zally.rule.api.Severity
import de.zalando.zally.rule.api.Violation
import de.zalando.zally.util.PatternUtil.isVersion

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
    val api_endpoints = "All API endpoints MUST begin with /api"
    val versioned = "All API endpoints MUST be versioned."
    val major_version = "Versioning MUST include only the major version in the URL path."
    // val backwards_compatible = "Any other changes to the resource MUST be backwards compatible"

    @Check(severity = Severity.MUST)
    fun validate(swagger: Swagger): Violation? {
        // check that version exists
        val version = swagger.info?.version
        val desc = when {
            version == null -> "Version does not exist"
            !isVersion(version) -> "Specified version has incorrect format: $version"
            else -> null
        }
        desc?.let { return Violation("$versioned $it", emptyList())}




    }
}