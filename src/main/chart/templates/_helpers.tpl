{{- define "edu_sharing_projects_wlo_bird_mdm_provider.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "edu_sharing_projects_wlo_bird_mdm_provider.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "edu_sharing_projects_wlo_bird_mdm_provider.labels" -}}
{{ include "edu_sharing_projects_wlo_bird_mdm_provider.labels.instance" . }}
helm.sh/chart: {{ include "edu_sharing_projects_wlo_bird_mdm_provider.chart" . }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{- define "edu_sharing_projects_wlo_bird_mdm_provider.labels.instance" -}}
{{ include "edu_sharing_projects_wlo_bird_mdm_provider.labels.app" . }}
{{ include "edu_sharing_projects_wlo_bird_mdm_provider.labels.version" . }}
{{- end -}}

{{- define "edu_sharing_projects_wlo_bird_mdm_provider.labels.version" -}}
version: {{ .Chart.AppVersion | quote }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end -}}

{{- define "edu_sharing_projects_wlo_bird_mdm_provider.labels.app" -}}
app: {{ include "edu_sharing_projects_wlo_bird_mdm_provider.name" . }}
app.kubernetes.io/name: {{ include "edu_sharing_projects_wlo_bird_mdm_provider.name" . }}
{{- end -}}

{{- define "edu_sharing_projects_wlo_bird_mdm_provider.image" -}}
{{- $registry := default .Values.global.image.registry .Values.image.registry -}}
{{- $repository := default .Values.global.image.repository .Values.image.repository -}}
{{ $registry }}{{ if $registry }}/{{ end }}{{ $repository }}{{ if $repository }}/{{ end }}
{{- end -}}
