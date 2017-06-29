<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:n="http://paket/nalog" xmlns:t="http://paket/tipovi" >
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/">
		<html>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<title>HTML Output</title>
				<title>Nalog</title>
				<style type="text/css">
					table {
					font-family: serif;
					border-collapse:
					collapse;
					margin: 10px auto 10px auto;
					width: 90%;
					}
					th, td {
					text-align: left;
					padding: 5px;
					}
					tr:nth-child(even){
					background-color: #f2f2f2 }
					th {
					background-color: #c1c1d7;
					font-family: sans-serif;
					color: white;
					}
					tr { border: 1px solid
					darkgrey; }
					tr:hover {
					font-style: italic;
					background-color: #cae8cb;
					}
					body { font-family: sans-serif; }
					p { text-indent: 5px; }
					.sup {
					vertical-align: super;
					padding-left: 4px;
					font-size: small;
					text-transform: lowercase;
					}

				</style>
			</head>
			<body>
				<h1>Nalog</h1>
				<p>
					Id poruke:
					<xsl:value-of select="//n:idPoruke" />
				</p>
				<p>
					Duznik:
					<xsl:value-of select="//n:duznik" />
				</p>
				<p>
					Svrha placanja:
					<xsl:value-of select="//n:svrhaPlacanja" />
				</p>
				<p>
					Primalac:
					<xsl:value-of select="//n:primalac" />
				</p>
				<p>
					Datum naloga:
					<xsl:value-of select="//n:datumNaloga" />
				</p>
				<p>
					Datum valute:
					<xsl:value-of select="//n:datumValute" />
				</p>
				<p>
					Racun duznika:
					<xsl:value-of select="//n:racunDuznika" />
				</p>
				<p>
					Model zaduzenja:
					<xsl:value-of select="//n:modelZaduzenja" />
				</p>
				<p>
					Poziv na broj zaduzenja:
					<xsl:value-of select="//n:pozivNaBrojZaduzenja" />
				</p>
				<p>
					Racun primaoca:
					<xsl:value-of select="//n:racunPrimaoca" />
				</p>
				<p>
					Model odobrenja:
					<xsl:value-of select="//n:modelOdobrenja" />
				</p>
				<p>
					Poziv na broj odobrenja:
					<xsl:value-of select="//n:pozivNaBrojOdobrenja" />
				</p>
				<p>
					Iznos:
					<xsl:value-of select="//n:iznos" />
				</p>
				<p>
					Oznaka valute:
					<xsl:value-of select="//n:oznakaValute" />
				</p>
				<p>
					Hitno ?
					<xsl:value-of select="//n:hitno" />
				</p>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
