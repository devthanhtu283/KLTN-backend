import pdfplumber
import re
def extract_text_from_pdf(pdf_path):
    text = ""
    with pdfplumber.open(pdf_path) as pdf:
        for page in pdf.pages:
            text += page.extract_text()
    return text

pdf_path = "data/thanhtucv.pdf"  # Thay đổi đường dẫn đến file CV của bạn
cv_text = extract_text_from_pdf(pdf_path)
print(cv_text)


def extract_sections(cv_text):
    sections = {
        "skills": [],
        "certificates": [],
        "projects": [],
        "education": [],
        "experience": []
    }

    # Trích xuất Skills
    skills_match = re.search(r"Skills:(.*?)Certificates:", cv_text, re.DOTALL)
    if skills_match:
        sections["skills"] = skills_match.group(1).strip().split("\n")

    # Trích xuất Certificates
    certificates_match = re.search(r"Certificates:(.*?)Projects:", cv_text, re.DOTALL)
    if certificates_match:
        sections["certificates"] = certificates_match.group(1).strip().split("\n")

    # Trích xuất Projects
    projects_match = re.search(r"Projects:(.*?)Education:", cv_text, re.DOTALL)
    if projects_match:
        sections["projects"] = projects_match.group(1).strip().split("\n")

    # Trích xuất Education
    education_match = re.search(r"Education:(.*?)Experience:", cv_text, re.DOTALL)
    if education_match:
        sections["education"] = education_match.group(1).strip().split("\n")

    # Trích xuất Experience
    experience_match = re.search(r"Experience:(.*)", cv_text, re.DOTALL)
    if experience_match:
        sections["experience"] = experience_match.group(1).strip().split("\n")

    return sections

sections = extract_sections(cv_text)

for section, content in sections.items():
    print(f"{section.capitalize()}:")
    for item in content:
        print(f" - {item}")
    print()