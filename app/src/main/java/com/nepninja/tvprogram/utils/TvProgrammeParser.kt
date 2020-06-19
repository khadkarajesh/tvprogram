package com.nepninja.tvprogram.utils

import android.util.Log
import com.nepninja.tvprogram.data.model.Channel
import com.nepninja.tvprogram.data.model.Programme
import com.nepninja.tvprogram.data.model.TvProgram
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

object TvProgrammeParser {
    private val TAG = TvProgrammeParser::class.simpleName
    fun getTvProgrammes(inputStream: InputStream): List<TvProgram> {
        val builderFactory = DocumentBuilderFactory.newInstance()
        val docBuilder = builderFactory.newDocumentBuilder()
        val doc = docBuilder.parse(inputStream)

        val channelNodes: NodeList = doc.getElementsByTagName("channel")
        val programmeNodes: NodeList = doc.getElementsByTagName("programme")

        val channels = getChannels(channelNodes)
        val programmes = getProgrammes(programmeNodes)

        return mapToTvProgrammes(channels, programmes)
    }

    private fun getChannels(
        channelNodes: NodeList
    ): List<Channel> {
        val channels = arrayListOf<Channel>()
        try {
            for (i in 0 until channelNodes.length) {
                if (channelNodes.item(0).nodeType == Node.ELEMENT_NODE) {
                    val element = channelNodes.item(i) as Element
                    val id = element.attributes.getNamedItem("id").nodeValue
                    val idNo = element.attributes.getNamedItem("idNo").nodeValue
                    val displayName =
                        element.getElementsByTagName("display-name").item(0)?.childNodes?.item(0)?.nodeValue
                    val icon = element.getElementsByTagName("icon")
                        .item(0).attributes.getNamedItem("src").nodeValue
                    channels.add(Channel(id = id, idNo = idNo, name = displayName, src = icon))
                }
            }
        } catch (e: ParserConfigurationException) {
            Log.e(TAG, "Channel ParseConfiguration exception ${e.localizedMessage}")
        } catch (e: SAXException) {
            Log.e(TAG, "Channel SAXException exception ${e.localizedMessage}")
        }
        return channels
    }


    private fun getProgrammes(programmeNodes: NodeList): List<Programme> {
        val programmes = arrayListOf<Programme>()
        try {
            for (i in 0 until programmeNodes.length) {
                val element = programmeNodes.item(i) as Element
                val id = element.attributes.getNamedItem("idNo").nodeValue
                val channelId = element.attributes.getNamedItem("channel").nodeValue
                val startDate =
                    DateUtils.strToDate(element.attributes.getNamedItem("start").nodeValue)
                val stopDate =
                    DateUtils.strToDate(element.attributes.getNamedItem("stop").nodeValue)
                val title =
                    element.getElementsByTagName("title").item(0)?.childNodes?.item(0)?.nodeValue
                val subTitle =
                    element.getElementsByTagName("sub-title")
                        .item(0)?.childNodes?.item(0)?.nodeValue
                val category =
                    element.getElementsByTagName("category").item(0)?.childNodes?.item(0)?.nodeValue
                val desc =
                    element.getElementsByTagName("desc").item(0)?.childNodes?.item(0)?.nodeValue
                val icon = element.getElementsByTagName("icon")
                    .item(0).attributes.getNamedItem("src").nodeValue
                programmes.add(
                    Programme(
                        startDate = startDate,
                        stopDate = stopDate,
                        channelId = channelId,
                        idNo = id,
                        title = title,
                        subTitle = subTitle,
                        description = desc,
                        category = getCategories(category),
                        src = icon
                    )
                )
            }
        } catch (e: ParserConfigurationException) {
            Log.e(TAG, "Programme ParserConfigurationException exception ${e.localizedMessage}")
        } catch (e: SAXException) {
            Log.e(TAG, "Programme SAXException exception ${e.localizedMessage}")
        }
        return programmes
    }


    private fun getCategories(category: String?): List<String> {
        if (category.isNullOrEmpty()) return emptyList()
        if (category.contains("[") && category.contains("]")) {
            val parsedCategory = category.substringBefore("]").substringAfter("[")
            if (parsedCategory.contains(",")) return parsedCategory.split(",")
            else parsedCategory
        }
        return arrayListOf(category)
    }

    private fun mapToTvProgrammes(
        channels: List<Channel>,
        programmes: List<Programme>
    ): List<TvProgram> {
        // 0(n^2) can reduce this complexity 0(n)
        // Will focus on later
        val tvProgrammes = arrayListOf<TvProgram>()
        channels.forEach { channel ->
            tvProgrammes.add(
                TvProgram(
                    programmes = programmes.filter { channel.idNo == it.idNo }.toList(),
                    channel = channel
                )
            )
        }
        return tvProgrammes
    }

}