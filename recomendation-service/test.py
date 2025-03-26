import spacy
from spacy.matcher import PhraseMatcher
import pandas as pd
import numpy as np
from xgboost import XGBClassifier
if not hasattr(XGBClassifier, "__sklearn_tags__"):
    XGBClassifier.__sklearn_tags__ = lambda self: {}
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.pipeline import Pipeline
from sklearn.compose import ColumnTransformer
from sklearn.metrics.pairwise import cosine_similarity
import mysql.connector
from flask import Flask, request, jsonify
from datetime import datetime

# Kết nối MySQL
def get_db_connection():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="",
        database="jobs"
    )

# Khởi tạo Flask app
app = Flask(__name__)

# Load spaCy model
nlp = spacy.load("vi_core_news_lg")
matcher = PhraseMatcher(nlp.vocab, attr="LOWER")
# Giả sử skills_dictionary là từ điển chuẩn hóa kỹ năng (bạn cần định nghĩa trước)
skills_dictionary = {
    "java script": "javascript",
    "node js": "node.js",
    "react js": "react.js",
    "reactjs": "react.js",
    "vue js": "vue.js",
    "vuejs": "vue.js",
    "angular js": "angular.js",
    "typescript.js": "typescript",
    "py": "python",
    "c sharp": "c#",
    "c plus plus": "c++",
    "go lang": "go",
    "ruby on rails": "ruby",
    "php hypertext preprocessor": "php",
    "swift programming": "swift",
    "kotlin lang": "kotlin",
    "rust lang": "rust",
    "dart lang": "dart",
    "html5": "html",
    "css3": "css",
    "scala lang": "scala",
    "perl script": "perl",
    "r lang": "r",
    "shell script": "shell scripting",
    "bash script": "bash",
    "powershell script": "powershell",
    "sql language": "sql",
    "pl sql": "pl/sql",
    "t sql": "t-sql",
    "java": "java",
    "python": "python",
    "javascript": "javascript",
    "dotnet": ".net",
    "net": ".net",
    "aws": "aws",
    "gcp": "gcp",
    "agile": "agile",
    "objective c": "objective-c",
    "flutter framework": "flutter",

    "bridge software engineer": "brse",
    "business analyst": "brse",
    "requirements analysis": "brse",
    "stakeholder communication": "brse",

    "mobile app development": "mobile development",
    "android development": "mobile development",
    "ios development": "mobile development",
    "react native": "mobile development",
    "flutter mobile": "mobile development",
    "xcode": "mobile development",
    "android studio": "mobile development",

    "devops engineer": "devops",
    "site reliability engineer": "sre",
    "ci/cd pipeline": "ci/cd",
    "continuous integration": "continuous integration",
    "continuous deployment": "continuous deployment",
    "ansible automation": "ansible",
    "terraform infrastructure": "terraform",
    "jenkins": "jenkins",
    "terraform": "terraform",
    "ci/cd": "ci/cd",
    "docker container": "docker",
    "kubernetes": "kubernetes",
    "prometheus": "prometheus",
    "grafana": "grafana",
    "artificial intelligence": "artificial intelligence",
    "machine learning": "machine learning",
    "deep learning": "machine learning",
    "natural language processing": "artificial intelligence",
    "computer vision": "artificial intelligence",
    "tensorflow ai": "tensorflow",
    "pytorch ai": "pytorch",
    "scikit-learn ml": "scikit-learn",
    "security engineer": "cybersecurity",
    "cyber security": "cybersecurity",
    "network security": "cybersecurity",
    "penetration testing": "cybersecurity",
    "ethical hacking": "cybersecurity",
    "firewall management": "cybersecurity",
    "intrusion detection": "cybersecurity",
    "siem": "cybersecurity",
    "palo alto": "cybersecurity",
    "fortinet": "cybersecurity",
    "network engineer": "networking",
    "network security": "networking",
    "lan configuration": "networking",
    "wan configuration": "networking",
    "vpn setup": "networking",
    "cisco networking": "networking",
    "juniper networking": "networking",
    "wireshark analysis": "networking",
    "nmap scanning": "networking",
    "spring boot": "springboot",
    "spring framework": "spring",
    "net": ".net",
    "net core": ".net core",
    "asp net": "asp.net",
    "asp net core": "asp.net core",
    "django framework": "django",
    "flask framework": "flask",
    "express js": "express.js",
    "laravel framework": "laravel",
    "flutter framework": "flutter",
    "jquery library": "jquery",
    "bootstrap framework": "bootstrap",
    "tensorflow library": "tensorflow",
    "pytorch library": "pytorch",
    "keras library": "keras",
    "hadoop framework": "hadoop",
    "spark framework": "spark",
    "hibernate framework": "hibernate",
    "ember js": "ember.js",
    "backbone js": "backbone.js",
    "vue js": "vue.js",
    "next js": "nextjs",
    "nuxt js": "nuxt.js",
    "svelte framework": "svelte",
    "nest js": "nestjs",
    "graphql js": "graphql",
    "opencv": "opencv",
    "torch": "torch",
    "scikit-learn": "scikit-learn",
    "adobe illustrator": "illustrator",
    "adobe photoshop": "photoshop",
    "adobe indesign": "indesign",
    "adobe creative suite": "adobe creative suite",
    "figma tool": "figma",
    "sketch tool": "sketch",
    "zeplin tool": "zeplin",
    "adobe xd": "xd",
    "my sql": "mysql",
    "postgres": "postgresql",
    "ms sql": "sql server",
    "oracle db": "oracle",
    "mongo db": "mongodb",
    "redis cache": "redis",
    "dynamodb aws": "dynamodb",
    "maria db": "mariadb",
    "elastic search": "elasticsearch",
    "agile methodology": "agile",
    "scrum framework": "scrum",
    "kanban board": "kanban",
    "ms project": "ms project",
    "jira software": "jira",
    "trello board": "trello",
    "waterfall methodology": "waterfall",
    "agile": "project management",
    "scrum": "project management",
    "kanban": "project management",
    "jira": "project management",
    "trello": "project management",
    "tiếng anh": "communication",
    "confluence": "confluence",
    "technical leadership": "technical lead",
    "tech leadership": "tech lead",
    "system architecture": "system architect",
    "solutions architect": "solution architect",
    "amazon web services": "aws",
    "microsoft azure": "azure",
    "google cloud platform": "gcp",
    "aws lambda": "aws lambda",
    "azure functions": "azure functions",

    "lập trình java": "java",
    "lập trình python": "python",
    "lập trình javascript": "javascript",
    "thiết kế web": "web design",
    "thiết kế giao diện": "ui/ux design",
    "kiểm thử phần mềm": "testing",
    "phát triển phần mềm": "software development",
    "quản trị hệ thống": "system administration",
    "an ninh mạng": "cybersecurity",
    "trí tuệ nhân tạo": "artificial intelligence",
    "học máy": "machine learning",
    "dữ liệu lớn": "big data",
    "đám mây": "cloud computing",
    "mạng máy tính": "networking",
    "quản lý dự án": "project management",
    "kiến trúc phần mềm": "software architecture",
    "dẫn dắt kỹ thuật": "tech lead",
    "hỗ trợ IT": "it support",
    "cơ sở dữ liệu": "database administration",
    "phát triển ứng dụng di động": "mobile development",
    "kỹ sư devops": "devops",
    "kỹ sư an ninh": "cybersecurity",
    "kỹ sư mạng": "networking",
    "kỹ sư phần mềm cầu nối": "brse",
    "perl": "perl",
    "groovy": "groovy",
    "kotlin": "kotlin",
    "lua": "lua",
    "go": "go",
    "typescript": "typescript",
    "scala": "scala",
    "ruby": "ruby",
    "php": "php",
    "swift": "swift",
    ".net": ".net",
    "react native": "react native",
    "angular": "angular",
    "node.js": "node.js",
    "vue.js": "vue.js",
    "spring": "spring",
    "django": "django",
    "flask": "flask",
    "jquery": "jquery",
    "vue": "vue",
    "react": "react",
    "html": "html",
    "css": "css",
    "bootstrap": "bootstrap",
    "docker": "docker",
    "kubernetes": "kubernetes",
    "jenkins": "jenkins",
    "ansible": "ansible",
    "terraform": "terraform",
    "git": "git",
    "svn": "svn",
    "mercurial": "mercurial",
    "junit": "junit",
    "selenium": "selenium",
    "testng": "testng",
    "pytest": "pytest",
    "mojolicious": "mojolicious",
    "catalyst": "catalyst",
    "grails": "grails",
    "ktor": "ktor",
    "spring": "spring",
    "lapis": "lapis",
    "orbit": "orbit",
    "gin": "gin",
    "echo": "echo",
    "fiber": "fiber",
    "beego": "beego",
    "nestjs": "nestjs",
    "play framework": "play framework",
    "akka": "akka",
    "ruby on rails": "ruby on rails",
    "sinatra": "sinatra",
    "laravel": "laravel",
    "symfony": "symfony",
    "magento": "magento",
    "codeigniter": "codeigniter",
    "zend framework": "zend framework",
    "vapor": "vapor",
    "perfect": "perfect",
    "asp.net": "asp.net",
    "asp.net core": "asp.net core",
    "blazor": "blazor",
    "expo": "expo",
    "ngrx": "ngrx",
    "angular material": "angular material",
    "express.js": "express.js",
    "koa": "koa",
    "hapi": "hapi",
    "nuxt.js": "nuxt.js",
    "vuex": "vuex",
    "vuetify": "vuetify",
    "spring boot": "spring boot",
    "spring security": "spring security",
    "django rest framework": "django rest framework",
    "flask-restful": "flask-restful",
    "flask-login": "flask-login",
    "docker-compose": "docker-compose",
    "helm": "helm",
    "blue ocean": "blue ocean",
    "jenkins pipeline": "jenkins pipeline",
    "selenide": "selenide",
    "fastapi": "fastapi",
    "flask": "flask",
    "rabbitmq": "rabbitmq",
    "celery": "celery",
    "apache kafka": "apache kafka",
    "kafka": "kafka",

    "mysql": "mysql",
    "postgresql": "postgresql",
    "mongodb": "mongodb",
    "oracle": "oracle",
    "sql server": "sql server",
    "redis": "redis",
    "sql": "sql",
    "nosql": "nosql",
    "pgadmin": "pgadmin",
    "sql developer": "sql developer",
    "datagrip": "datagrip",

    "tcp/ip": "tcp/ip",
    "http": "http",
    "https": "https",
    "ftp": "ftp",
    "router": "router",
    "switch": "switch",
    "firewall": "firewall",
    "owasp": "owasp",
    "burp suite": "burp suite",
    "nmap": "nmap",
    "wireshark": "wireshark",
    "penetration testing": "penetration testing",
    "ethical hacking": "ethical hacking",
    "kali linux": "penetration testing",
    "metasploit": "penetration testing",
    "owasp zap": "penetration testing",
    "ida pro": "malware analysis",
    "ghidra": "malware analysis",
    "radare2": "malware analysis",
    "autopsy": "digital forensics",
    "ftk imager": "digital forensics",
    "encase": "digital forensics",

    "aws": "aws",
    "azure": "azure",
    "google cloud platform": "google cloud platform",
    "ec2": "ec2",
    "s3": "s3",
    "lambda": "lambda",
    "azure functions": "azure functions",
    "hadoop": "hadoop",
    "spark": "spark",
    "hive": "hive",
    "pig": "pig",

    "linux": "linux",
    "windows server": "windows server",
    "system administration": "system administration",
    "it support": "it support",
    "machine learning": "machine learning",
    "deep learning": "deep learning",
    "figma": "figma",
    "adobe xd": "adobe xd",
    "sketch": "sketch"

}
skill_patterns = [nlp(text) for text in skills_dictionary.keys()]
matcher.add("SKILL", None, *skill_patterns)

# Hàm chuẩn hóa kỹ năng
def extract_skills(text):
    if not isinstance(text, str):
        return set()
    skills = set()
    for skill in text.split(","):
        skill = skill.strip().lower()
        standardized = skills_dictionary.get(skill, skill.title())
        skills.add(standardized)
    return skills

# Hàm tính Jaccard similarity (chỉ tính trên các kỹ năng liên quan)
def jaccard_similarity(cv_skills, job_skills):
    if not cv_skills or not job_skills:
        return 0.0
    # Chỉ lấy các kỹ năng của CV có liên quan đến job
    relevant_cv_skills = cv_skills.intersection(job_skills)
    if not relevant_cv_skills:
        return 0.0

    intersection = len(relevant_cv_skills)
    union = len(job_skills)  
    return intersection / union


def load_data_from_db():
    conn = get_db_connection()
    cursor = conn.cursor()
    
    query_job = """
    SELECT id, title, description, required_skills 
    FROM job
    """
    df_job = pd.read_sql(query_job, conn)
    df_job.columns = ['Job_ID', 'Title', 'Job Description', 'Required_Skills']
    
    query_cv = """
    SELECT id, skills 
    FROM cv
    """
    df_cv = pd.read_sql(query_cv, conn)
    df_cv.columns = ['CV_ID', 'Skills']
    
    conn.close()
    return df_job, df_cv

def train_models(df_job):
    df_job['combined_text'] = df_job['Required_Skills'].apply(lambda x: str(x))
    df_job['extracted_skills'] = df_job['Required_Skills'].apply(extract_skills)
    
    job_tfidf_vectorizer = TfidfVectorizer()
    job_tfidf_matrix = job_tfidf_vectorizer.fit_transform(df_job['combined_text'])
    
    return job_tfidf_vectorizer, job_tfidf_matrix, df_job

# Hàm gợi ý công việc
def recommend_jobs_for_cv_model(cv_text, cv_skills, job_df, job_tfidf_vectorizer, job_tfidf_matrix, alpha=0.5, beta=0.5, threshold=0.1):
    cv_vec = job_tfidf_vectorizer.transform([cv_text])
    cosine_sim = cosine_similarity(cv_vec, job_tfidf_matrix).flatten()
    jaccard_scores = job_df['extracted_skills'].apply(lambda js: jaccard_similarity(cv_skills, js)).values
    hybrid_score = alpha * cosine_sim + beta * jaccard_scores
    
    print(f"CV Skills: {cv_skills}")
    sorted_indices = np.argsort(hybrid_score)[::-1]
    
    recommendations = []
    for idx in sorted_indices:
        if hybrid_score[idx] < threshold:
            continue
        job_skills = job_df.loc[idx, 'extracted_skills']
        matched = job_skills.intersection(cv_skills) if isinstance(job_skills, set) else set()
        if matched:
            print(f"Job ID: {job_df.loc[idx, 'Job_ID']}, Job Skills: {job_skills}, Cosine Sim: {cosine_sim[idx]}, Jaccard Score: {jaccard_scores[idx]}, Hybrid Score: {hybrid_score[idx]}")
            recommendations.append((job_df.loc[idx, 'Job_ID'], ', '.join(matched)))
    
    return recommendations


def remove_outdated_matches(cv_id, cv_skills, df_job):
    conn = get_db_connection()
    cursor = conn.cursor()
    
    query_matches = """
    SELECT cv_id, job_id, matched_skill
    FROM matches
    WHERE cv_id = %s
    """
    cursor.execute(query_matches, (cv_id,))
    matches = cursor.fetchall()
    
    records_to_delete = []
    
    for match in matches:
        cv_id, job_id, matched_skill = match
        job_skills = df_job[df_job['Job_ID'] == job_id]['extracted_skills'].iloc[0]
        matched = job_skills.intersection(cv_skills) if isinstance(job_skills, set) else set()
        if not matched:  # Nếu không còn kỹ năng nào khớp
            records_to_delete.append((cv_id, job_id))
    

    if records_to_delete:
        delete_query = """
        DELETE FROM matches
        WHERE cv_id = %s AND job_id = %s
        """
        cursor.executemany(delete_query, records_to_delete)
        conn.commit()
        print(f"Deleted {len(records_to_delete)} outdated matches for CV ID {cv_id}")
    
    conn.close()


@app.route('/recommend', methods=['POST'])
def recommend_jobs():
    df_job, df_cv = load_data_from_db()
    job_tfidf_vectorizer, job_tfidf_matrix, df_job = train_models(df_job)
    
    df_cv['combined_text'] = df_cv['Skills'].apply(lambda x: str(x))
    df_cv['extracted_skills'] = df_cv['Skills'].apply(extract_skills)
    
    recommendation_rows = []
    for idx, row in df_cv.iterrows():
        cv_id = int(row['CV_ID'])
        cv_text = row['combined_text']
        cv_skills = row['extracted_skills']
        
        remove_outdated_matches(cv_id, cv_skills, df_job)
        

        rec = recommend_jobs_for_cv_model(cv_text, cv_skills, df_job, job_tfidf_vectorizer, job_tfidf_matrix, alpha=0.5, beta=0.5, threshold=0.1)
        
        for job_id, matched in rec:
            recommendation_rows.append({
                'cv_id': cv_id,
                'job_id': int(job_id),
                'matched_skill': matched,
                'time_matches': datetime.now().strftime('%Y-%m-%d %H:%M:%S'),
                'status': 1
            })
    

    conn = get_db_connection()
    cursor = conn.cursor()
    
    insert_query = """
    INSERT INTO matches (cv_id, job_id, matched_skill, time_matches, status)
    VALUES (%s, %s, %s, %s, %s)
    ON DUPLICATE KEY UPDATE
        matched_skill = VALUES(matched_skill),
        time_matches = VALUES(time_matches),
        status = VALUES(status)
    """
    for rec in recommendation_rows:
        cursor.execute(insert_query, (
            rec['cv_id'],
            rec['job_id'],
            rec['matched_skill'],
            rec['time_matches'],
            rec['status']
        ))
    
    conn.commit()
    conn.close()
    
    return jsonify({
        'status': 'success',
        'message': f'Inserted or updated {len(recommendation_rows)} recommendations into database'
    })

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)