package ru.unn.agile.CurrencyConverter.Infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.net.URL;
import java.util.Locale;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.CurrencyIndexes;
import ru.unn.agile.CurrencyConverter.Provider.ICurrencyProvider;

public class CentralBankCurrencyProvider implements ICurrencyProvider {
    private static final String CENTRAL_BANK_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private ArrayList<Currency> actualCurrency = null;

    @Override
    public final ArrayList<Currency> getActualCurrencyRates() {
        if (actualCurrency == null) {
            updateCurrencyRates();
        }
        return actualCurrency;
    }

    public void updateCurrencyRates() {
        actualCurrency = new ArrayList<Currency>();

        Document doc = getXMLDocumentWithActualCurrencyRates();
        if (doc == null) {
            return;
        }

        NodeList nList = doc.getElementsByTagName("Valute");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                addNodeElementToCurrencyList(eElement, actualCurrency);
            }
        }

        actualCurrency.add(CurrencyIndexes.RUB.getIndex(),
                Currency.builder().numCode(1).charCode("RUB").name("Российский рубль")
                        .nominal(1).value(1).build());
    }

    private static Document getXMLDocumentWithActualCurrencyRates() {
        Document doc = null;
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL cbr = new URL(CENTRAL_BANK_URL);
            InputStream is = cbr.openStream();
            doc = db.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private static void addNodeElementToCurrencyList(final Element element,
                                                     final ArrayList<Currency> currencyList) {
        int numCode = Integer.parseInt(
                element.getElementsByTagName("NumCode").item(0).getTextContent());

        CurrencyIndexes index = CurrencyIndexes.getCurrencyIndexByNumCode(numCode);
        if (index != null) {
            String charCode =
                    element.getElementsByTagName("CharCode").item(0).getTextContent();
            int nominal = Integer.parseInt(
                    element.getElementsByTagName("Nominal").item(0).getTextContent());
            String name =
                    element.getElementsByTagName("Name").item(0).getTextContent();

            NumberFormat format = NumberFormat.getInstance(new Locale("ru"));
            double value = 1;
            try {
                value = format.parse(element.getElementsByTagName("Value")
                        .item(0).getTextContent()).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            currencyList.add(index.getIndex(),
                             Currency.builder().numCode(numCode).charCode(charCode)
                                               .name(name).nominal(nominal).value(value).build());
        }
    }
}
