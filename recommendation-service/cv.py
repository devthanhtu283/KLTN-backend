from fastapi import FastAPI, File, UploadFile
import pdfplumber
import docx
import spacy
from typing import List
import io
import re

app = FastAPI()
nlp = spacy.load("en_core_web_sm")

SKILLS = {
    "python", "java", "javascript", "typescript", "c", "c++", "c#", "go", "php", "ruby",
    "kotlin", "swift", "dart", "scala", "r", "rust", "perl", "elixir", "haskell", "objective-c",
    "html", "css", "scss", "sass", "less", "bootstrap", "tailwind css", "javascript", "jquery", "pug", "ejs",
    "react", "reactjs", "angular", "angularjs", "vue", "svelte", "ember.js", "backbone.js", "next.js", "nuxt.js",
    "node.js", "express.js", "nestjs", "spring", "spring boot", "django", "flask", "fastapi",
    "laravel", "symfony", "rails", "asp.net", "adonisjs", "phoenix", "hapi", "koa", ".net", "wordpress",
    "flutter", "react native", "swift", "kotlin", "java (android)", "android studio", "xamarin",
    "cordova", "ionic", "objective-c", "swift (ios)", "xcode", "unity", "unreal engine", "cocos2d",
    "mysql", "postgresql", "sqlite", "oracle", "sql server", "mariadb", "mongodb", "firebase",
    "dynamodb", "cassandra", "neo4j", "elasticsearch", "couchbase", "couchdb", "influxdb", "hbase", "redis", "memcached", "riak","arangodb",
    "docker", "kubernetes", "terraform", "ansible", "jenkins", "github actions", "gitlab ci", "circleci", "helm",
    "aws", "azure", "gcp", "cloudflare", "vercel", "netlify", "heroku", "firebase hosting", "elastic beanstalk",
    "nginx", "apache", "caddy", "haproxy", "traefik", "cloudfront", "cloudflare cdn", "fastly", "akamai",
    "apache spark", "hadoop", "kafka", "airflow", "flink", "beam", "hive", "hbase", "sqoop",
    "dbt", "presto", "delta lake", "iceberg", "snowflake", "bigquery", "redshift", "athena", "databricks",
    "tensorflow", "pytorch", "keras", "scikit-learn", "pandas", "numpy", "scipy", "matplotlib", "seaborn",
    "plotly", "bokeh", "altair", "ggplot", "shiny", "dash", "streamlit", "flask", "fastapi", "django",
    "pandas", "numpy", "scikit-learn", "matplotlib", "seaborn", "tensorflow", "keras", "pytorch",
    "xgboost", "lightgbm", "huggingface", "transformers", "langchain", "llamaindex", "mlflow",
    "openai", "spacy", "nltk", "opencv", "yolov5", "detectron2", "fastai", "pycaret", "auto-sklearn",
    "scrapy", "beautifulsoup", "selenium", "puppeteer", "requests", "httpx", "axios", "fetch",
    "oop", "design patterns", "mvc", "microservice", "monolith", "event-driven", "soa", "clean architecture","ASP NET Core MVC"
    "hexagonal architecture", "tdd", "bdd", "ddd", "agile", "scrum", "kanban", "ci/cd", "devops", "git",
    "jwt", "oauth2", "keycloak", "saml", "https", "ssl/tls", "xss", "csrf", "sql injection",
    "owasp top 10", "hashing", "encryption", "rbac", "abac", "oauth2", "openid connect", "sso", "saml",
    "rest api", "graphql", "grpc", "soap", "openapi", "swagger", "postman", "axios", "feign", "retrofit",
    "websockets", "mqtt", "amqp", "kafka", "rabbitmq", "zeromq", "grpc", "protobuf", "thrift",
    "junit", "testng", "jest", "mocha", "chai", "pytest", "unittest", "cypress", "selenium",
    "playwright", "postman tests", "karate", "robot framework", "cucumber", "load testing", "jmeter",
    "kafka", "rabbitmq", "mqtt", "socket.io", "websocket", "zeromq", "pubsub", "eventbridge", "sns", "sqs",
    "kinesis", "firehose", "dynamodb streams", "kafka streams", "spark streaming", "flink", "beam",
    "aws lambda", "azure functions", "google cloud functions", "vercel", "netlify", "heroku", "cloud run",
    "cloud functions", "cloudflare workers", "fastly", "akamai", "lambda@edge", "cloudfront",
    "prometheus", "grafana", "elk stack", "elasticsearch", "logstash", "kibana", "datadog",
    "new relic", "graylog", "sentry",
    "git", "github", "gitlab", "bitbucket", "jira", "trello", "confluence", "figma", "draw.io",
    "lucidchart", "vscode", "intellij", "eclipse", "slack", "notion",
    "solidity", "ethereum", "web3.js", "ethers.js", "nft", "defi", "metamask", "truffle", "hardhat", "ipfs",
    "blockchain", "smart contract", "dapp", "dao", "ipfs", "zk-snarks", "zk-rollups", "plasma",
    "decentralized", "centralized", "distributed", "consensus", "proof of work", "proof of stake",
    "arduino", "raspberry pi", "stm32", "esp32", "iot core", "rtos", "freertos", "mqtt", "can bus"
}

def extract_text_from_pdf(file) -> str:
    text = ""
    with pdfplumber.open(file) as pdf:
        for page in pdf.pages:
            page_text = page.extract_text()
            if page_text:
                text += page_text + "\n"
    return text

def extract_text_from_docx(file) -> str:
    doc = docx.Document(file)
    return "\n".join([para.text for para in doc.paragraphs])

def extract_skills(text: str) -> List[str]:
    text_lower = text.lower()
    return sorted(list({skill for skill in SKILLS if skill in text_lower}))

def extract_experience_blocks(text: str) -> List[dict]:
    lines = [re.sub(r"[^\x00-\x7F]+", "", line.strip()) for line in text.splitlines() if line.strip()]
    experiences = []
    time_pattern = re.compile(r"(\d{1,2}/\d{4})\s*[-–to]+\s*(\d{1,2}/\d{4}|now|present)", re.IGNORECASE)

    i = 0
    while i < len(lines) - 2:
        line = lines[i]
        match = time_pattern.search(line.lower())
        if match:
            time = f"{match.group(1)} - {match.group(2)}"
            company = lines[i + 1].strip()
            position = lines[i + 2].strip()
            desc_lines = []
            i += 3
            while i < len(lines) and (lines[i].startswith("-") or lines[i].startswith("•")):
                desc_lines.append(lines[i])
                i += 1

            experiences.append({
                "position": position,
                "company": company,
                "time": time,
                "desc": "\n".join(desc_lines)
            })
        else:
            i += 1
    return experiences

def extract_experience_flexible(text: str) -> List[dict]:
    if "WORK EXPERIENCE" in text:
        text = text.split("WORK EXPERIENCE", 1)[1]

    # Chỉ cắt khi dòng là đúng tên section
    lines = text.splitlines()
    section_keywords = ["PROJECT", "PROJECTS", "EDUCATION", "CERTIFICATION", "SKILLS", "REFERENCES"]
    final_lines = []
    for line in lines:
        if any(line.strip().upper() == keyword for keyword in section_keywords):
            break
        final_lines.append(line)
    text = "\n".join(final_lines)

    # Thử dạng chứa //
    lines = [re.sub(r"[^\x00-\x7F]+", "", line.strip()) for line in text.splitlines() if line.strip()]
    experiences = []
    time_pattern = re.compile(r"(\d{1,2}/\d{4})\s*[-–to]+\s*(\d{1,2}/\d{4}|present|now)", re.IGNORECASE)
    position_keywords = ["engineer", "developer", "intern", "manager", "tester", "freelancer", "support"]

    for i, line in enumerate(lines):
        lower = line.lower()
        if any(keyword in lower for keyword in position_keywords) and time_pattern.search(line):
            time_match = time_pattern.search(line)
            raw_pos = line.split('//')[0].strip() if '//' in line else line.split(time_match.group(1))[0].strip()

            prefixes_to_remove = [
                "skills", "communicate in english", "in english", "objective", "summary",
                "about", "profile", "of rest api", "project in", "project", "certification", "personal"
            ]
            for prefix in prefixes_to_remove:
                if raw_pos.lower().startswith(prefix):
                    raw_pos = raw_pos[len(prefix):].strip()

            position = raw_pos
            time = f"{time_match.group(1)} - {time_match.group(2)}"
            company = None

            if i + 1 < len(lines):
                next_line = lines[i + 1].strip()
                if not next_line.startswith("•") and not time_pattern.search(next_line):
                    company = next_line

            desc_lines = []
            j = i + 2
            while j < len(lines) and (lines[j].startswith("-") or lines[j].startswith("•")):
                desc_lines.append(lines[j])
                j += 1

            experiences.append({
                "position": position,
                "company": company,
                "time": time,
                "desc": "\n".join(desc_lines)
            })

    if not experiences:
        return extract_experience_blocks(text)
    return experiences

@app.post("/extract")
async def extract_cv(file: UploadFile = File(...)):
    ext = file.filename.lower()
    content = await file.read()

    if ext.endswith(".pdf"):
        text = extract_text_from_pdf(io.BytesIO(content))
    elif ext.endswith(".docx"):
        text = extract_text_from_docx(io.BytesIO(content))
    else:
        return {"error": "Chỉ hỗ trợ PDF hoặc DOCX"}

    skills = extract_skills(text)
    experience = extract_experience_flexible(text)

    return {
        "skills": skills,
        "experience": experience
    }
@app.get("/hello")
async def hello():
    return "aaaa"

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("cv:app", host="0.0.0.0", port=8000, reload=True)
