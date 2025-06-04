import requests
from bs4 import BeautifulSoup
import csv

url = "https://hades.fandom.com/wiki/Nocturnal_Arms"
r = requests.get(url)
soup = BeautifulSoup(r.text, "html.parser")
table_rows = soup.find("span", id="Weapons").find_next("table").find_all("tr")

name_cols = []
for row in table_rows:
    cols = row.find_all("td")
    if cols:
        name_col = cols[1]
        if name_col.find("a"):
            name = name_col.find("a").get("title")
            name_cols.append(name.replace(" ", "_").replace("'", "%27"))

weapons_url = "https://hades.fandom.com/wiki/{}"
weapon_aspects = []

def scrape_weapon_aspects():
    for name in name_cols:
        r = requests.get(weapons_url.format(name))
        soup = BeautifulSoup(r.text, "html.parser")
        
        table = soup.find("span", id="Aspects").find_next("table")
        if not table:
            continue
        tbody = table.find("tbody")
        rows = tbody.find_all("tr")

        for row in rows:
            cols = row.find_all("td")
            if cols:
                aspect_col = cols[1]
                if aspect_col.find("b"):
                    aspect_name = aspect_col.find("b").text.strip()
                    weapon_name = name.replace("_", " ").replace("%27", "'")
                    weapon_aspects.append({"Weapon": weapon_name, "Aspect": aspect_name})

scrape_weapon_aspects()

with open("weapon_aspects.csv", "w", newline="", encoding="utf-8") as csvfile:
    fieldnames = ["Weapon", "Aspect"]
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

    writer.writeheader()
    writer.writerows(weapon_aspects)

