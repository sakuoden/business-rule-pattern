# 概要
自己学習用。

DDDにおけるDomainをビジネスルールとして焦点を当てた場合における、抽象データ型のパターンまとめ。

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

## パターン1: バリエーション

ドメイン(ビジネスルール)におけるバリエーションを下記の２つの方法でコードで仕様化する。

### ①バリエーションを入り口で分ける
2020/03/17 の段階で良さそうなのはアプリケーション層のクラス（当然ドメインモデル、ドメインモデルの集約も分ける）で分けてしまうやり方。
同じクラス内に同じif文が重複していたらこれを検討した方が良い。

### ②バリエーションをenumを利用して仕様化する
ドメインの１つのバリエーションは複数のenumに分解されるケースが多い。

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

## パターン2: 表形式
ドメイン(ビジネスルール)において表形式でまとめられるものを下記の2つの方法で実装する。
表の交差点の値は全て同じ種類の値にする（例: 可・不可、日数、金額、率など）

### ①表形式A(enum × メソッド)
1つのenumで表を表現する。

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

### ②表形式B(emum × enum)
下記の型を表オブジェクトとしてラップする。
EnumMap<Enum, EnumMap<Enum, ValueObject>>

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

## パターン3: 状態モデル
パターン2: 表形式 との違いは、表形式は交差した値に関心事があり、状態モデルは交差点の値が遷移する事に関心事がある。

なお状態モデルで「なぜ状態を管理したいのか」を明確にしないとオーバーエンジニアリングになりがち。

（なぜ管理するのかの例）

- ダメな状態遷移の際にアラート出したい
- ダメな状態遷移の際にロギングしたい
- ダメな状態遷移の際に例外でアプリを止めたい
- ダメな状態遷移の際に画面に出してユーザーに知らせたい


### ①状態モデルA
EnumMap<Enum(状態), EnumSet<Enum(イベント)>>

| |イベントセット|
|---|---|
|状態A|イベントA,B,C|
|状態B|イベントA,D|

（具体例）

- [LockerStateTransitions](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/locker/LockerStateTransitions.kt)

| |イベントセット|
|---|---|
|空|確保|
|確保中|預入, 確保取消|
|預入中|受取|


### ②状態モデルB
EnumMap<Enum(状態), EnumSet<Enum(状態)>

ある状態から、あるイベントを起因として別の状態に遷移できるかどうかを表す。

| |状態A|状態B|状態C|状態D|
|---|---|---|---|---|
|状態A|イベントW|遷移不可|遷移不可|イベントZ|
|状態B|遷移不可|遷移不可|イベントY|遷移不可|
|状態C|イベントw|イベントX|イベントY|-|
|状態D|-|-|-|-|


（具体例）

- [RingiStateTransitions](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/model/ringi/RingiStateTransitions.kt)


||稟議中|稟議差戻し中|稟議済み|棄却|
|---|---|---|---|---|
|稟議中|-|差戻し|決済|棄却|
|稟議差戻し中|再稟議|-|-|-|
|稟議済み|-|-|-|-|
|棄却|-|-|-|-|


## パターン4: 計算式
エリックエヴァンスのDDDでは、ユビキタス言語とドメインオブジェクトを一致させる事を推奨している。

しかし計算式を表す値オブジェクトは基本的な操作だけの型に分解して、それらの基本操作型をまとめてユビキタス言語の型を作る方が良い。

(具体例)
- [Yen](https://github.com/sakuoden/business-rule-pattern/blob/master/src/main/kotlin/com/jackthenewest/businessrulepattern/domain/type/yen/Yen.kt)

## パターン5
### ●集合
### ●列
### ●写像



