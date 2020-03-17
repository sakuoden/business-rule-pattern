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

# 重要な事
パターンより前に重要なのはドメイン(ビジネスルール)を発見する事。
それはすぐに発見できるものではなくリファクタリングを重ねる事で次第に見えてくるもの。

ドメイン(ビジネスルール)の発見には、RDRA2.0やビジネスルールの発見・定義のヒントが役に立つ。

# パターン集

## バリエーションの仕様化

まず、ドメイン(ビジネスルール)におけるバリエーションを下記の２つの方法でコードで仕様化する。

### ①バリエーションを入り口で分ける
2020/03/17 の段階で良さそうなのはアプリケーション層のクラス（当然ドメインモデル、ドメインモデルの集約も分ける）で分けてしまうやり方。
同じクラス内に同じif文が重複していたらこれを検討した方が良い。

### ②バリエーションをenumを利用して仕様化する
この段階でバリエーションをenumによって整理することで名前を整理する。
RDRAのバリエーションは分解する事を検討する。

(具体例)
- [CustomerType](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/customer/CustomerType.kt)

|顧客区分|
|---|
|シニア|
|大人|
|子供|

- [DiscountType](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/discount/DiscountType.kt)

|割引区分|
|---|
|早朝|
|深夜|

## パターン1: 表形式
ドメイン(ビジネスルール)において表形式にまとめられるものは、このパターンで実装する。
状態モデルも表形式と似ているが状態モデルは、アクセスできる横軸が制限されることに違いがある。

### ●表形式A(enum × メソッド)
メソッドにより表の値を取得する。
表形式Bは縦軸も横軸もenumで実装されている場合に使う。
こちらは横軸はenumで実装されていない場合に利用する。

| |メソッド名A|メソッド名B|
|---|---|---|
|enum値A|値1|値2|
|enum値B|値3|値4|

（具体例）
- [FeeType](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/fee/FeeType.kt)

|料金種別 |yen()|feeName()|
|---|---|---|
|シニア|600|シニア料金|
|大人|1000|大人料金|
|子供|500|子供料金|

### ●表形式B(emum × enum)
縦軸も横軸もenumで実装されている場合に利用する。

||enum値A|enum値B|
|---|---|---|
|enum値C|値1|値2|
|enum値D|値3|値4|

(具体例)
- [DiscountYen](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/discount/DiscountYen.kt)

|割引額|早朝|深夜|
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



