import requests
from bs4 import BeautifulSoup
import csv


# Hades not included 
gods_1 = ["Aphrodite", "Ares", "Artemis", "Athena", "Chaos", "Demeter", "Dionysus",
        "Hermes", "Poseidon", "Zeus"]
# gods_1 = ["Aphrodite"]
# Selene is excluded
gods_2 = ["Apollo", "Hephaestus", "Hera", "Hestia"]

base_url_1 = 'https://hades.fandom.com/wiki/{}/Boons_(Hades_II)'
base_url_2 = 'https://hades.fandom.com/wiki/{}/Boons'

boons_data = []

def scrape_boons():
    for god in gods_1:
        url = base_url_1.format(god)
        print(f"Scraping {url}...")

        r = requests.get(url)
        soup = BeautifulSoup(r.text, "html.parser")

        list_tag = soup.find("span", id="List")
        if not list_tag:
            continue
        table = list_tag.find_next("table")
        if not table:
            continue
        body = table.find("tbody")
        rows = body.find_all("tr")

        for row in rows:
            cols = row.find_all("td")
            if len(cols) > 1:
                name_col = cols[1]
                bold = name_col.find("b")
                if bold:
                    name = bold.text.strip()
                    desc = name_col.text.strip()
                    boons_data.append({
                        "name": name,
                        "god": god,
                        "description": desc
                    })

def scrape_boons_2():
    for god in gods_2:
        url = base_url_2.format(god)
        print(f"Scraping {url}...")

        r = requests.get(url)
        soup = BeautifulSoup(r.text, "html.parser")

        list_tag = soup.find("span", id="List")
        if not list_tag:
            continue
        table = list_tag.find_next("table")
        if not table:
            continue
        body = table.find("tbody")
        rows = body.find_all("tr")

        for row in rows:
            cols = row.find_all("td")
            if len(cols) > 1:
                name_col = cols[1]
                bold = name_col.find("b")
                if bold:
                    name = bold.text.strip()
                    desc = name_col.text.strip()
                    boons_data.append({
                        "name": name,
                        "god": god,
                        "description": desc
                    })

scrape_boons()
scrape_boons_2()

with open("boons.csv", "w", newline="", encoding="utf-8") as csvfile:
    fieldnames = ["name", "god", "description"]
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

    writer.writeheader()
    writer.writerows(boons_data)

