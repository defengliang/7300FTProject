## How to run

### Requisite

Make sure the env has JRE/JDK 17 or above installed.

To check if the env has JRE/JDK 17 installed, run the command:

```
java -version
```

If you see something like this, that means JRE/JDK 17 has installed, if not, please google or ask ChatGRT to install JRE/JDK17 to your env.


```
openjdk version "17.0.9" 2023-10-17
OpenJDK Runtime Environment Homebrew (build 17.0.9+0)
OpenJDK 64-Bit Server VM Homebrew (build 17.0.9+0, mixed mode, sharing)
```

### Checkout git or download 

Checkout the repository or download the ft-1.0.jar file.

### Run

In the folder where ft-1.0.jar is, run the command:

```
java -jar ft-1.0.jar XXX
```

Replace the XXX with the stock symbol from Yahoo Finance, for example, AAPL is Apple's stock symbol, and GOOG is Google's stock symbol.

If you see an output as follows, it means it has finished

```
Files generated!
```

You will see four files generated under the same folder.

xxx_converted.atff, xxx_raw.arff, XXX_rel.arff, XXX_stock_info.csv

After the files are generated, we open the XXX_ref.arff file in Weka.

Please refer to the document below on how to operate in Weka.




