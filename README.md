<img src="alerter-logo.svg" alt="drawing" style="width:150px;"/>

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](https://www.rultor.com/b/yegor256/rultor)](https://www.rultor.com/p/yegor256/rultor)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/h1alexbel/alerter-mail/actions/workflows/maven.yml/badge.svg)](https://github.com/h1alexbel/alerter-mail/actions/workflows/maven.yml)
[![Hits-of-Code](https://hitsofcode.com/github/h1alexbel/alerter-mail)](https://hitsofcode.com/view/github/h1alexbel/alerter-mail)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/h1alexbel/alerter-mail/blob/master/LICENSE)

[Alerter](https://t.me/AlerterMailBot) is an Email assistant/[Telegram](https://telegram.org) chatbot. It helps you to deliver PDF documents faster. 

## What Problems Does Alerter Solve?

Automated PDF artifacts delivering. Alerter attempts to reduce an effort that you do to email and attach PDF document.

## How It Works?
![msg](img.png)

it does exactly this:

1. Reads the message you provide
2. Gets URL of PDF document via Telegram API
3. Forms an email
4. Sends it
5. Reports back to you.

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 11+.