# 概要
自己学習用。

DDDにおけるDomainをビジネスルールに焦点を当てた場合における、抽象データ型のパターンまとめ。

増田亨さん考案のCCSRによる開発を想定。

【参考文献】
- 増田亨さん [現場で役立つシステム設計の原則](https://www.amazon.co.jp/%E7%8F%BE%E5%A0%B4%E3%81%A7%E5%BD%B9%E7%AB%8B%E3%81%A4%E3%82%B7%E3%82%B9%E3%83%86%E3%83%A0%E8%A8%AD%E8%A8%88%E3%81%AE%E5%8E%9F%E5%89%87-%E3%80%9C%E5%A4%89%E6%9B%B4%E3%82%92%E6%A5%BD%E3%81%A7%E5%AE%89%E5%85%A8%E3%81%AB%E3%81%99%E3%82%8B%E3%82%AA%E3%83%96%E3%82%B8%E3%82%A7%E3%82%AF%E3%83%88%E6%8C%87%E5%90%91%E3%81%AE%E5%AE%9F%E8%B7%B5%E6%8A%80%E6%B3%95-%E5%A2%97%E7%94%B0-%E4%BA%A8-ebook/dp/B073GSDBGT)
- 神崎善司さん [RDRA2.0](https://www.amazon.co.jp/RDRA2-0-%E3%83%8F%E3%83%B3%E3%83%89%E3%83%96%E3%83%83%E3%82%AF-%E8%BB%BD%E3%81%8F%E6%9F%94%E8%BB%9F%E3%81%A7%E7%B2%BE%E5%BA%A6%E3%81%AE%E9%AB%98%E3%81%84%E8%A6%81%E4%BB%B6%E5%AE%9A%E7%BE%A9%E3%81%AE%E3%83%A2%E3%83%87%E3%83%AA%E3%83%B3%E3%82%B0%E6%89%8B%E6%B3%95-%E7%A5%9E%E5%B4%8E%E5%96%84%E5%8F%B8-ebook/dp/B07STQZFBX)

# CCSR概要
RDRA2.0による要件定義<br>
　↑   ↓<br>
仕様化<br>
　↑   ↓<br>
実装

# パターン集

## パターン1
### ●バリエーション
このバリエーションを利用して、下記のパターンを表現する。

(具体例)
- [CustomerType](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/customer/CustomerType.kt)

|顧客区分|
|---|
|大人|
|子供|

## パターン2
### ●表形式A

| |メソッド名A|メソッド名B|
|---|---|---|
|enum値A|値1|値2|
|enum値B|値3|値4|

（具体例）
- [FeeType](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/fee/FeeType.kt)

|料金種別 |yen()|
|---|---|
|シニア|600|
|大人|1000|
|子供|500|

### ●表形式B 

| |enum値A|enum値B|
|---|---|---|
|enum値C|値1|値2|
|enum値D|値3|値4|

(具体例)
- [DiscountYen](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/discount/DiscountYen.kt)

| |早朝|深夜|
|---|---|---|
|シニア|100|200|
|大人|50|100|
|子供|150|300|

## パターン3
### ●状態モデルA

| |イベントA|イベントB|
|---|---|---|
|状態A|値1|値2|
|状態B|値3|値4|

### ●状態モデルB

| |イベントA|イベントB|
|---|---|---|
|状態A|値1|値2|
|状態B|値3|値4|

## パターン4
### ●計算
(具体例)
- [Yen](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/type/yen/Yen.kt)

## パターン5
### ●集合
### ●列
### ●写像



