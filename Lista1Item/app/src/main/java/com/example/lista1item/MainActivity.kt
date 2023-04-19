package com.example.lista1item

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter : Adapter
        var arrayCoresList: ArrayList<Cores> = ArrayList()

        var lista = mutableListOf<Cores>()
        var text: String = ""
        var valor: String = ""
        val context = this.applicationContext
        val res = context.resources
        val xmlStr = res.getXml(R.xml.nomes)
        var eventType = xmlStr.eventType
        while (eventType != XmlPullParser.END_DOCUMENT){
            when (eventType){
                XmlPullParser.START_TAG -> if (xmlStr.name.equals("cor", ignoreCase = true)){
                    val attr = xmlStr.getAttributeName(0)
                    valor = xmlStr.getAttributeValue(0).toString()
                }
                XmlPullParser.TEXT ->  {
                    text = xmlStr.text
                    var cor = Cores(valor, text)
                    lista.add(cor)
                }

                else -> {
                }
            }
            eventType = xmlStr.next()
        }
        //
        //
        //
        //

        lista.forEach{
            arrayCoresList.add(it)
        }

        adapter = MyAdapter(this, arrayCoresList)
        var mListView = findViewById<ListView>(R.id.coresList)
        mListView.adapter = adapter
    }
}

class MyAdapter(private val context: Context,
                private val arrayList: ArrayList<Cores>): BaseAdapter(){
                    private lateinit var texto1: TextView
                    private lateinit var texto2: TextView
    override fun getCount(): Int {

        return arrayList.size
    }

    override fun getItem(position: Int): Any{
        return position
    }

    override fun getItemId(position: Int): Long{
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{

        var convertView = LayoutInflater.from(context).inflate(
            R.layout.cust_layout,
            parent,
            false
        )
        texto1 = convertView.findViewById(R.id.nome)     //text1)
        texto2 = convertView.findViewById(R.id.valor)     //text2)
        var img: ImageView = convertView.findViewById(R.id.icon)

        if (position%2==0){
            img.setImageResource(R.drawable.img)
        }


        texto1.text = arrayList[position].nome
        texto2.text = arrayList[position].valor
        return convertView
    }
}