package main

import (
	"fmt"
	"io"
	"net/http"
	"os/exec"
	"strings"

	"github.com/PuerkitoBio/goquery"
)

func fetchHTML(url string) string {
	fmt.Println("# downloading the webpage...")

	resp, err := http.Get(url)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		panic(fmt.Sprintf("fetchHTML: bad status: %s", resp.Status))
	}

	content, err := io.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}

	return string(content)
}

func extractPDFURL(html string) string {
	doc, err := goquery.NewDocumentFromReader(strings.NewReader(html))
	if err != nil {
		panic(err)
	}

	var bitstreamUUID string
	doc.Find(`a[href*="/view/"]`).Each(func(i int, s *goquery.Selection) {
		if bitstreamUUID != "" {
			return
		}
		href, exists := s.Attr("href")
		if !exists {
			return
		}
		// href: /items/{item-uuid}/view/{bitstream-uuid}
		parts := strings.Split(href, "/view/")
		if len(parts) == 2 {
			bitstreamUUID = strings.Trim(parts[1], "/")
		}
	})

	if bitstreamUUID == "" {
		panic("extractPDFURL: no match")
	}

	return "https://dea.lib.unideb.hu/bitstreams/" + bitstreamUUID + "/download"
}

func openInBrowser(url string) error {
	return exec.Command("open", url).Start()
}

func readURL() string {
	fmt.Println("A hallgatói dolgozatok (Informatikai Kar) itt érhetők el: https://dea.lib.unideb.hu")
	fmt.Println("Egy szakdolgozat URL-je így néz ki (példa): https://dea.lib.unideb.hu/items/ed260496-92b4-428e-a8a5-5c9bd0c0f28f")
	fmt.Println()

	var urlStr string
	fmt.Print("A letöltendő szakdolgozat URL-je: ")
	fmt.Scanln(&urlStr)

	return urlStr
}

func main() {
	urlStr := readURL()
	html := fetchHTML(urlStr)
	pdfURL := extractPDFURL(html)
	fmt.Println("# URL of the PDF:")
	fmt.Println("#", pdfURL)
	fmt.Println("# opening the PDF in your web browser...")
	err := openInBrowser(pdfURL)
	if err != nil {
		panic(err)
	}
}
