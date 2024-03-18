package org.example
import com.sun.deploy.xml.XMLParser
import org.json.JSONException
import org.json.JSONObject
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.error.YAMLException
import javax.management.modelmbean.XMLParseException

interface Parser
{
    fun parse(text: String): Map<String, String>
}

class JsonParser : Parser
{
    override fun parse(text: String): Map<String, String>
    {
        val map = mutableMapOf<String, String>()
        try
        {
            val jsonObject = JSONObject(text)
            map.put(text, jsonObject.toString())
        }
        catch (e: JSONException)
        {
            println("JSON parsing error: ${e.message}")
        }
        return map
    }
}

class XmlParser : Parser
{
    override fun parse(text: String): Map<String, String>
    {
        val map = mutableMapOf<String, String>()
        try
        {
            map.put(text, XMLParser(text).toString())
        }
        catch (e: XMLParseException)
        {
            println("XML parsing error: ${e.message}")
        }
        return map
    }
}

class YamlParser : Parser
{
    override fun parse(text: String): Map<String, String>
    {
        val map = mutableMapOf<String, String>()
        val yaml = Yaml()
        try
        {
            map.put(text, yaml.load(text))
        }
        catch (e : YAMLException)
        {
            println("YAML parsing error: ${e.message}")
        }
        return map
    }
}