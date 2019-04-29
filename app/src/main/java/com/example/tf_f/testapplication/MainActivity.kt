package com.example.tf_f.testapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var nStr : String = "0" //入力値 項の保持
    val numList = ArrayList<Double>() // 数式の各項を保持
    val opeList = ArrayList<Char>() // 数式に含まれるオペレーションの保持


    //演算
    fun calcualte() : Double {

        var i = 0
        while (i < opeList.size) {

            //先に掛け算、割り算を前から順に行う
            if(opeList.get(i) == '*' || opeList.get(i) == '/' || opeList.get(i) == '%') {
                var result = 0.0

                if (opeList.get(i) == '*'){
                    result = numList.get(i) * numList.get(i+1)
                }else if(opeList.get(i) == '/'){
                    result = numList.get(i) / numList.get(i+1)
                }else{
                    //intにキャストして計算してからdoubleにキャスト
                    result =( numList.get(i).toInt() % numList.get(i+1).toInt() ) .toDouble()
                }

                numList.set(i,result)   //計算に使った一つ目の数を計算結果に置き換え
                numList.removeAt(i+1)   //二つ目の数をリストから削除
                opeList.removeAt(i)     //使い終わった演算子をリストから削除
                i--                   //リストの次の要素が一つ手前に来たのでiを一つ戻す
            }

            // 引き算を足し算に変える
            else if(opeList.get(i) == '-'){
                opeList.set(i,'+')
                numList.set(i+1,numList.get(i+1) * -1) //引く数を-1倍
            }
            i++
        }

        // 足し算だけ残るので、リストに残った数を合計する
        var result = 0.0
        for (i in numList){
            result += i
        }
        return result
    }

    fun addList(str : String, ope : Char) {
        try {
            var num = str.toDouble()         //小数に変換
            numList.add(num)                   //nListに追加
            if (ope != '=') opeList.add(ope)   //演算子をoListに追加
        }catch(e:Exception){
            Value_Output.text = "Numeric error"   //小数に変換できなかった時にエラーを表示
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //各値の入力

        plus_minus.setOnClickListener{
            if(!nStr.isEmpty()) {
                if (nStr[0] == '-') {
                    nStr = nStr.substring(1, nStr.length - 1)
                } else {
                    nStr = "-" + nStr
                }
                Value_Output.text = nStr
            }
        }


        num0.setOnClickListener {
            if(nStr.length == 1 && nStr[0] == '0') {
                nStr = ""
                Value_Output.text = ""
            }
            Value_Output.text = "${Value_Output.text}0"
            nStr += "0"

        }

        num1.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                nStr = ""
                Value_Output.text = ""
            }
            Value_Output.text = "${Value_Output.text}1"
            nStr += "1"

        }

        num2.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}2"
            nStr += "2"
        }

        num3.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}3"
            nStr += "3"
        }

        num4.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}4"
            nStr += "4"
        }

        num5.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}5"
            nStr += "5"
        }

        num6.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}6"
            nStr += "6"
        }

        num7.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}7"
            nStr += "7"
        }

        num8.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}8"
            nStr += "8"
        }

        num9.setOnClickListener {
            if (nStr.length == 1 && nStr[0] == '0') {
                Value_Output.text = ""
                nStr = ""
            }
            Value_Output.text = "${Value_Output.text}9"
            nStr += "9"
        }

        dot.setOnClickListener {
            Value_Output.text = "${Value_Output.text}."
            nStr += "."
        }

        reset.setOnClickListener {
            Value_Output.text = "0"
            nStr = "0"
            numList.clear()
            opeList.clear()
        }

        del.setOnClickListener {
            var formulaStr = Value_Output.text.toString()
            if (!formulaStr.isEmpty()) {
                Value_Output.text = formulaStr.subSequence(0,formulaStr.lastIndex)
            }
            if (!nStr.isEmpty()) {
                nStr = nStr.substring(0, nStr.lastIndex)
            }

            if(nStr.isEmpty()){
                nStr = "0"
                Value_Output.text = "0"
            }
        }

        equal.setOnClickListener {
            Value_Output.text = "${Value_Output.text}="
            addList(nStr,'=')
            var result = calcualte().toString()
            Value_Output.text = result
            nStr = result
            numList.clear()
            opeList.clear()
        }


        //四則演算
        add.setOnClickListener {
            if(!nStr.isEmpty()) {
                Value_Output.text = "${Value_Output.text}+"
                addList(nStr, '+')
                nStr = ""
            }
            /*else{
                opeList[opeList.size - 1] = '+'
                Value_Output.text = "${}+"
            }
            */
        }

        substract.setOnClickListener {
            if(!nStr.isEmpty()) {
                Value_Output.text = "${Value_Output.text}-"
                addList(nStr, '-')
                nStr = ""
            }
        }

        multiply.setOnClickListener {
            if(!nStr.isEmpty()) {
                Value_Output.text = "${Value_Output.text}*"
                addList(nStr, '*')
                nStr = ""
            }
        }

        divid.setOnClickListener {
            if(!nStr.isEmpty()) {
                Value_Output.text = "${Value_Output.text}/"
                addList(nStr, '/')
                nStr = ""
            }
        }

        mod.setOnClickListener {
            if(!nStr.isEmpty()) {
                Value_Output.text = "${Value_Output.text}%"
                addList(nStr, '%')
                nStr = ""
            }
        }





    }




}
