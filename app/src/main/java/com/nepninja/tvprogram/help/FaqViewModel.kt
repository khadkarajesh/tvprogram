package com.nepninja.tvprogram.help

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.model.Faq

class FaqViewModel(app: Application) : BaseViewModel(app) {
    var faqs: MutableLiveData<List<Faq>> = MutableLiveData()

    init {
        var data = arrayListOf(
            Faq(
                " Where are your application development centers based?",
                "We have development centers in several cities across Ireland, US, and few of european country including Norway"
            ),
            Faq(
                "How many development resources do you usually assign to a project?",
                "The number of resources employed for a project depends entirely upon the scale and complexity of the project. For example - we allocate two developers, one tester and a part-time UI designer for a small project. In addition, each project will have a Technical Architect, Business Analyst and Project Manager. We can increase the number of resources depending upon the customer/project requirements."
            ),
            Faq(
                "Can you provide the summary of the experience and skills of each of your application development resources?",
                "We are bound by the confidentiality agreement, and will be unable to share these details. A large number of full-time developers are a part of our pool, working on different in-house and client projects. At different times, the available resources are different and unique. You can be assured that only the best quality, trained and experienced resources will work on your project and meet your requirements."
            ),
            Faq(
                " Where are your application development centers based?",
                "We have development centers in several cities across Ireland, US, and few of european country including Norway"
            ),
            Faq(
                "How many development resources do you usually assign to a project?",
                "The number of resources employed for a project depends entirely upon the scale and complexity of the project. For example - we allocate two developers, one tester and a part-time UI designer for a small project. In addition, each project will have a Technical Architect, Business Analyst and Project Manager. We can increase the number of resources depending upon the customer/project requirements."
            ),
            Faq(
                "Can you provide the summary of the experience and skills of each of your application development resources?",
                "We are bound by the confidentiality agreement, and will be unable to share these details. A large number of full-time developers are a part of our pool, working on different in-house and client projects. At different times, the available resources are different and unique. You can be assured that only the best quality, trained and experienced resources will work on your project and meet your requirements."
            ),
            Faq(
                " Where are your application development centers based?",
                "We have development centers in several cities across Ireland, US, and few of european country including Norway"
            ),
            Faq(
                "How many development resources do you usually assign to a project?",
                "The number of resources employed for a project depends entirely upon the scale and complexity of the project. For example - we allocate two developers, one tester and a part-time UI designer for a small project. In addition, each project will have a Technical Architect, Business Analyst and Project Manager. We can increase the number of resources depending upon the customer/project requirements."
            ),
            Faq(
                "Can you provide the summary of the experience and skills of each of your application development resources?",
                "We are bound by the confidentiality agreement, and will be unable to share these details. A large number of full-time developers are a part of our pool, working on different in-house and client projects. At different times, the available resources are different and unique. You can be assured that only the best quality, trained and experienced resources will work on your project and meet your requirements."
            )
        )
        faqs.postValue(data)
    }
}