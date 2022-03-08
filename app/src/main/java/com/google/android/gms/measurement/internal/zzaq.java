package com.google.android.gms.measurement.internal;

import andhook.lib.xposed.callbacks.XCallback;
import android.content.Context;
import com.google.android.gms.internal.measurement.zzch;
import com.google.android.gms.internal.measurement.zzcw;
import com.google.android.gms.internal.measurement.zzjp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzaq {
    public static zzen<Double> zzan;
    public static zzen<Integer> zzm;
    private static List<zzen<?>> zzct = Collections.synchronizedList(new ArrayList());
    private static Set<zzen<?>> zzcu = Collections.synchronizedSet(new HashSet());
    public static zzen<Long> zza = zza("measurement.ad_id_cache_time", 10000L, 10000L, zzat.zza);
    public static zzen<Long> zzb = zza("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, zzas.zza);
    public static zzen<Long> zzc = zza("measurement.config.cache_time", 86400000L, 3600000L, zzbf.zza);
    public static zzen<String> zzd = zza("measurement.config.url_scheme", "https", "https", zzbo.zza);
    public static zzen<String> zze = zza("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", zzcb.zza);
    public static zzen<Integer> zzf = zza("measurement.upload.max_bundles", 100, 100, zzck.zza);
    public static zzen<Integer> zzg = zza("measurement.upload.max_batch_size", 65536, 65536, zzcx.zza);
    public static zzen<Integer> zzh = zza("measurement.upload.max_bundle_size", 65536, 65536, zzdg.zza);
    public static zzen<Integer> zzi = zza("measurement.upload.max_events_per_bundle", 1000, 1000, zzdt.zza);
    public static zzen<Integer> zzj = zza("measurement.upload.max_events_per_day", 100000, 100000, zzec.zza);
    public static zzen<Integer> zzk = zza("measurement.upload.max_error_events_per_day", 1000, 1000, zzav.zza);
    public static zzen<Integer> zzl = zza("measurement.upload.max_public_events_per_day", 50000, 50000, zzau.zza);
    public static zzen<Integer> zzn = zza("measurement.upload.max_realtime_events_per_day", 10, 10, zzaw.zza);
    public static zzen<Integer> zzo = zza("measurement.store.max_stored_events_per_app", 100000, 100000, zzaz.zza);
    public static zzen<String> zzp = zza("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", zzay.zza);
    public static zzen<Long> zzq = zza("measurement.upload.backoff_period", 43200000L, 43200000L, zzbb.zza);
    public static zzen<Long> zzr = zza("measurement.upload.window_interval", 3600000L, 3600000L, zzba.zza);
    public static zzen<Long> zzs = zza("measurement.upload.interval", 3600000L, 3600000L, zzbd.zza);
    public static zzen<Long> zzt = zza("measurement.upload.realtime_upload_interval", 10000L, 10000L, zzbc.zza);
    public static zzen<Long> zzu = zza("measurement.upload.debug_upload_interval", 1000L, 1000L, zzbe.zza);
    public static zzen<Long> zzv = zza("measurement.upload.minimum_delay", 500L, 500L, zzbh.zza);
    public static zzen<Long> zzw = zza("measurement.alarm_manager.minimum_interval", 60000L, 60000L, zzbg.zza);
    public static zzen<Long> zzx = zza("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, zzbj.zza);
    public static zzen<Long> zzy = zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, zzbi.zza);
    public static zzen<Long> zzz = zza("measurement.upload.initial_upload_delay_time", 15000L, 15000L, zzbl.zza);
    public static zzen<Long> zzaa = zza("measurement.upload.retry_time", 1800000L, 1800000L, zzbk.zza);
    public static zzen<Integer> zzab = zza("measurement.upload.retry_count", 6, 6, zzbn.zza);
    public static zzen<Long> zzac = zza("measurement.upload.max_queue_time", 2419200000L, 2419200000L, zzbm.zza);
    public static zzen<Integer> zzad = zza("measurement.lifetimevalue.max_currency_tracked", 4, 4, zzbp.zza);
    public static zzen<Integer> zzae = zza("measurement.audience.filter_result_max_count", 200, 200, zzbr.zza);
    public static zzen<Integer> zzaf = zza("measurement.upload.max_public_user_properties", 25, 25, null);
    public static zzen<Integer> zzag = zza("measurement.upload.max_event_name_cardinality", 500, 500, null);
    public static zzen<Integer> zzah = zza("measurement.upload.max_public_event_params", 25, 25, null);
    public static zzen<Long> zzai = zza("measurement.service_client.idle_disconnect_millis", 5000L, 5000L, zzbq.zza);
    public static zzen<Boolean> zzaj = zza("measurement.test.boolean_flag", false, false, zzbt.zza);
    public static zzen<String> zzak = zza("measurement.test.string_flag", "---", "---", zzbs.zza);
    public static zzen<Long> zzal = zza("measurement.test.long_flag", -1L, -1L, zzbv.zza);
    public static zzen<Integer> zzam = zza("measurement.test.int_flag", -2, -2, zzbu.zza);
    public static zzen<Integer> zzao = zza("measurement.experiment.max_ids", 50, 50, zzbw.zza);
    public static zzen<Integer> zzap = zza("measurement.max_bundles_per_iteration", 2, 2, zzbz.zza);
    public static zzen<Boolean> zzaq = zza("measurement.validation.internal_limits_internal_event_params", false, false, zzby.zza);
    public static zzen<Boolean> zzar = zza("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", true, true, zzca.zza);
    public static zzen<Boolean> zzas = zza("measurement.collection.firebase_global_collection_flag_enabled", true, true, zzcd.zza);
    public static zzen<Boolean> zzat = zza("measurement.collection.efficient_engagement_reporting_enabled_2", true, true, zzcc.zza);
    public static zzen<Boolean> zzau = zza("measurement.collection.redundant_engagement_removal_enabled", false, false, zzcf.zza);
    public static zzen<Boolean> zzav = zza("measurement.client.freeride_engagement_fix", true, true, zzce.zza);
    public static zzen<Boolean> zzaw = zza("measurement.experiment.enable_experiment_reporting", true, true, zzch.zza);
    public static zzen<Boolean> zzax = zza("measurement.collection.log_event_and_bundle_v2", true, true, zzcg.zza);
    public static zzen<Boolean> zzay = zza("measurement.quality.checksum", false, false, null);
    public static zzen<Boolean> zzaz = zza("measurement.sdk.dynamite.allow_remote_dynamite2", false, false, zzcj.zza);
    public static zzen<Boolean> zzba = zza("measurement.sdk.collection.validate_param_names_alphabetical", true, true, zzci.zza);
    public static zzen<Boolean> zzbb = zza("measurement.collection.event_safelist", true, true, zzcl.zza);
    private static zzen<Boolean> zzcv = zza("measurement.service.audience.invalidate_config_cache_after_app_unisntall", true, true, zzcn.zza);
    public static zzen<Boolean> zzbc = zza("measurement.service.audience.fix_skip_audience_with_failed_filters", true, true, zzcm.zza);
    public static zzen<Boolean> zzbd = zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false, false, zzcp.zza);
    public static zzen<Boolean> zzbe = zza("measurement.audience.refresh_event_count_filters_timestamp", false, false, zzco.zza);
    public static zzen<Boolean> zzbf = zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false, false, zzcr.zza);
    public static zzen<Boolean> zzbg = zza("measurement.sdk.collection.retrieve_deeplink_from_bow_2", true, true, zzcq.zza);
    public static zzen<Boolean> zzbh = zza("measurement.sdk.collection.last_deep_link_referrer2", true, true, zzct.zza);
    public static zzen<Boolean> zzbi = zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false, false, zzcs.zza);
    public static zzen<Boolean> zzbj = zza("measurement.sdk.collection.last_gclid_from_referrer2", false, false, zzcv.zza);
    public static zzen<Boolean> zzbk = zza("measurement.sdk.collection.enable_extend_user_property_size", true, true, zzcu.zza);
    public static zzen<Boolean> zzbl = zza("measurement.upload.file_lock_state_check", false, false, zzcw.zza);
    public static zzen<Boolean> zzbm = zza("measurement.sampling.calculate_bundle_timestamp_before_sampling", true, true, zzcz.zza);
    public static zzen<Boolean> zzbn = zza("measurement.ga.ga_app_id", false, false, zzcy.zza);
    public static zzen<Boolean> zzbo = zza("measurement.lifecycle.app_backgrounded_tracking", true, true, zzdb.zza);
    public static zzen<Boolean> zzbp = zza("measurement.lifecycle.app_in_background_parameter", false, false, zzda.zza);
    public static zzen<Boolean> zzbq = zza("measurement.integration.disable_firebase_instance_id", false, false, zzdd.zza);
    public static zzen<Boolean> zzbr = zza("measurement.lifecycle.app_backgrounded_engagement", false, false, zzdc.zza);
    public static zzen<Boolean> zzbs = zza("measurement.collection.service.update_with_analytics_fix", false, false, zzdf.zza);
    public static zzen<Boolean> zzbt = zza("measurement.service.use_appinfo_modified", false, false, zzde.zza);
    public static zzen<Boolean> zzbu = zza("measurement.client.firebase_feature_rollout.v1.enable", true, true, zzdh.zza);
    public static zzen<Boolean> zzbv = zza("measurement.client.sessions.check_on_reset_and_enable2", true, true, zzdj.zza);
    public static zzen<Boolean> zzbw = zza("measurement.config.string.always_update_disk_on_set", true, true, zzdi.zza);
    public static zzen<Boolean> zzbx = zza("measurement.scheduler.task_thread.cleanup_on_exit", false, false, zzdl.zza);
    public static zzen<Boolean> zzby = zza("measurement.upload.file_truncate_fix", false, false, zzdk.zza);
    public static zzen<Boolean> zzbz = zza("measurement.engagement_time_main_thread", true, true, zzdn.zza);
    public static zzen<Boolean> zzca = zza("measurement.sdk.referrer.delayed_install_referrer_api", false, false, zzdm.zza);
    public static zzen<Boolean> zzcb = zza("measurement.sdk.screen.disabling_automatic_reporting", false, false, zzdp.zza);
    public static zzen<Boolean> zzcc = zza("measurement.sdk.screen.manual_screen_view_logging", false, false, zzdo.zza);
    public static zzen<Boolean> zzcd = zza("measurement.gold.enhanced_ecommerce.format_logs", true, true, zzdr.zza);
    public static zzen<Boolean> zzce = zza("measurement.gold.enhanced_ecommerce.nested_param_daily_event_count", true, true, zzdq.zza);
    public static zzen<Boolean> zzcf = zza("measurement.gold.enhanced_ecommerce.upload_nested_complex_events", true, true, zzds.zza);
    public static zzen<Boolean> zzcg = zza("measurement.gold.enhanced_ecommerce.log_nested_complex_events", true, true, zzdv.zza);
    public static zzen<Boolean> zzch = zza("measurement.gold.enhanced_ecommerce.updated_schema.client", true, true, zzdu.zza);
    public static zzen<Boolean> zzci = zza("measurement.gold.enhanced_ecommerce.updated_schema.service", true, true, zzdx.zza);
    private static zzen<Boolean> zzcw = zza("measurement.collection.synthetic_data_mitigation", false, false, zzdw.zza);
    public static zzen<Boolean> zzcj = zza("measurement.service.configurable_service_limits", false, false, zzdz.zza);
    public static zzen<Boolean> zzck = zza("measurement.client.configurable_service_limits", false, false, zzdy.zza);
    public static zzen<Boolean> zzcl = zza("measurement.androidId.delete_feature", true, true, zzeb.zza);
    public static zzen<Boolean> zzcm = zza("measurement.client.global_params.dev", false, false, zzea.zza);
    public static zzen<Boolean> zzcn = zza("measurement.service.global_params", false, false, zzed.zza);
    public static zzen<Boolean> zzco = zza("measurement.service.global_params_in_payload", true, true, zzef.zza);
    public static zzen<Boolean> zzcp = zza("measurement.client.string_reader", true, true, zzee.zza);
    public static zzen<Boolean> zzcq = zza("measurement.sdk.attribution.cache", true, true, zzeh.zza);
    public static zzen<Long> zzcr = zza("measurement.sdk.attribution.cache.ttl", 604800000L, 604800000L, zzeg.zza);
    public static zzen<Boolean> zzcs = zza("measurement.service.database_return_empty_collection", true, true, zzej.zza);

    public static Map<String, String> zza(Context context) {
        zzch zza2 = zzch.zza(context.getContentResolver(), zzcw.zza("com.google.android.gms.measurement"));
        return zza2 == null ? Collections.emptyMap() : zza2.zza();
    }

    private static <V> zzen<V> zza(String str, V v, V v2, zzel<V> zzelVar) {
        zzen<V> zzenVar = new zzen<>(str, v, v2, zzelVar);
        zzct.add(zzenVar);
        return zzenVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ Long zzco() {
        if (zzek.zza != null) {
            zzx zzxVar = zzek.zza;
        }
        return Long.valueOf(zzjp.zzc());
    }

    static {
        Integer valueOf = Integer.valueOf((int) XCallback.PRIORITY_HIGHEST);
        zzm = zza("measurement.upload.max_conversions_per_day", valueOf, valueOf, zzax.zza);
        Double valueOf2 = Double.valueOf(-3.0d);
        zzan = zza("measurement.test.double_flag", valueOf2, valueOf2, zzbx.zza);
    }
}
