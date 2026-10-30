# Taifex — TWSE Stock Overview (Android)

An Android app that pulls daily market data from the Taiwan Stock Exchange (TWSE) Open API and shows every listed stock with its price, volume and valuation metrics in one list.

<!-- Add 2–3 screenshots here: the stock list, the sort sheet, and the valuation dialog -->

## Features

- Lists all TWSE-listed stocks with opening, high, low and closing price, change, volume and trade value
- Merges three separate TWSE endpoints into one record per stock, matched by stock code
- Sorts the list by stock code, ascending or descending, from a bottom sheet
- Tap a stock to see its P/E ratio, dividend yield and P/B ratio

## Tech stack

| Area | Choice |
| --- | --- |
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM (ViewModel + Repository) |
| Async / state | Kotlin Coroutines, StateFlow |
| Networking | Retrofit, Gson |

## Architecture

```
HomeScreen (Compose)
   │  collects StateFlow
   ▼
StockViewModel ── sorting, selection, loading state
   │
   ▼
StockRepository ── calls 3 endpoints, merges by stock code
   │
   ▼
StockApi (Retrofit) ── TWSE Open API
```

TWSE splits the data across three endpoints:

| Endpoint | Data |
| --- | --- |
| `STOCK_DAY_ALL` | Daily price, volume, trade value |
| `STOCK_DAY_AVG_ALL` | Closing price, monthly average price |
| `BWIBBU_ALL` | P/E ratio, dividend yield, P/B ratio |

The repository uses the daily trade list as the base and joins the other two by stock code, so the UI only deals with one `StockData` model.

## Run it

1. Clone the repo and open it in Android Studio.
2. Run on a device or emulator with Android 7.0 (API 24) or later.

No API key is needed. The TWSE Open API is public.

## Data source

[TWSE Open API](https://openapi.twse.com.tw/)
