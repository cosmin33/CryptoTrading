# CryptoTrading
Multi market cryptocurrency trading with multi-zoomable charts and one-click orders

## Features
- stackable, conditional order tree: ex. buy at x, then:
  - if bought, then trailing stop at diff and sell at x
  - if not then sell at y
  - and so on, you may construct orders on your projected variants
- buy or sell at the market best price: this will keep your order in front of the ask/bid stack until the sum is satisfied or a certain threshold is passed. Good for enterint into a pump & dump race. Carefull with this...
- zoomable graphics with changing time interval: have you wanted to see what's beneath that one day candlestick bar ? Was it hard to find that specific interval on 1h candlestick ? This kind of zoom is very annoying to make. Not anymore...

