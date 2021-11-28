WEATHER SHOPPER UI

Tech-
1. Selenium
2. TestNg
3. Java

Steps to Run:
1. Clone the repo and switch to branch "First"
2. run mvn install
3. run mvn clean test -DsuiteXmlFile=testng.xml -DtestEnv=PROD
The testNg file will run same testcase on chrome and firefox sequentially

Testcase File --> src/test/java/TestScenario1.java

TestCase that can be automated ->
1. User opens the web application and selects Moisturizer or sunscreen to buy.
2. In the product page , user adds same product twice to the cart
3. cart value should be displayed as 2 and clicks on cart button
4. In checkout page user should be shown the added product name and price be 2 * price_of_single_product and total price should also be calculated according to it
    (Bug is present at this point)